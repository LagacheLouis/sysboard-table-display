package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import cern.mpe.systems.core.domain.SystemUnderTest;
import cern.mpe.systems.core.domain.relation.SystemRelation;
import cern.mpe.systems.core.service.control.SystemRelationControllerImpl;
import cern.mpe.systems.core.service.control.SystemsControllerImpl;
import cern.mpe.systems.core.service.manage.SynchronizedMultiMapSystemRelationEngine;
import cern.mpe.systems.core.service.manage.SystemAttributesManagerImpl;
import cern.mpe.systems.core.service.manage.SystemRelationManagerImpl;
import cern.mpe.systems.core.service.manage.SystemsManagerImpl;
import cern.mpe.systems.core.service.provider.SystemRelationProvider;
import cern.mpe.systems.core.service.provider.SystemsProvider;
import datacore.AbstractDataLayer;
import debugprovider.RandomGenRelations;
import debugprovider.RandomGenSystems;
import filters.Filter;
import filters.FilterName;
import filters.FilterTest;
import filters.FilterType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataLayerImpl extends AbstractDataLayer{

	private static DataLayerImpl instance;
	
	private DataLayerImpl() {
		super();
	}
	
	public final static DataLayerImpl getInstance() {

        if (DataLayerImpl.instance == null) {
           synchronized(DataLayerImpl.class) {
             if (DataLayerImpl.instance == null) {
               DataLayerImpl.instance = new DataLayerImpl();
               DataLayerImpl.getInstance().isFirstLaunch = true;
             }
           }
        }
        return DataLayerImpl.instance;
    }
	
	//setup of systemsManager, systems providers, etc
	@Override
	protected void setUpSystems()
	{
		systemsManager = new SystemsManagerImpl();
		systemProviders = new ArrayList<SystemsProvider>();
		RandomGenSystems test = new RandomGenSystems();
		systemProviders.add(test);
		systemsManager.setSystemsProviders(systemProviders);
        SystemAttributesManagerImpl systemAttributesManager = new SystemAttributesManagerImpl();
        systemController = new SystemsControllerImpl();
        systemController.setSystemsManager(systemsManager);
        systemController.setSystemAttributesManager(systemAttributesManager);
        systemsManager.init();
	}
	
	@Override
	protected void setUpSystemRelations(){
		relationManager = new SystemRelationManagerImpl();
		relationProviders = new ArrayList<SystemRelationProvider>();
		relationEngine = new SynchronizedMultiMapSystemRelationEngine();
		
		RandomGenRelations randomRelationProvider = new RandomGenRelations();
		randomRelationProvider.genAllRelations(systemsManager);
		relationProviders.add(randomRelationProvider);
		relationManager.setSystemRelationEngine(relationEngine);
		relationManager.setSystemsManager(systemsManager);
		relationManager.setSystemRelationProviders(relationProviders);
		relationManager.init();
		relationController = new SystemRelationControllerImpl();
		relationController.setSystemRelationManager(relationManager);
		relationController.init();
	}
	
	@Override
	protected void setUpFilters(){
		filters = new HashSet<>();
		filters.add(new FilterName());
		filters.add(new FilterType());
		filters.add(new FilterTest());
		for(Filter filter : filters){
			filter.callInitialize();
		}
	}

	@Override
	protected Collection<TableItem> convertToDisplayList(Collection<SystemUnderTest> listSystemsUnderTest)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		ObservableList<TableItem> data = FXCollections.observableArrayList();
		System.out.println();
		int index = 0;
		for(SystemUnderTest item : systemsUnderTest)
		{
			String props[] = 
				{
					Integer.toString(index),
					item.getName(),item.getKey().toDbString(),
					relationController.getSystemRelations(item,
					SystemRelation.class).toString()
				};
			index++;
			try {
				data.add(new TableItem(props));
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	
}
