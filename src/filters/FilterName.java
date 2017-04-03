package filters;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;

import cern.mpe.systems.core.domain.SystemUnderTest;

/**
 * @author Lagache
 *
 */
public class FilterName extends Filter{
	
	@Override
	public Collection<SystemUnderTest> filter(Collection<SystemUnderTest> listUnderTest) {
		Collection<SystemUnderTest> list = new HashSet<>();
		String valSearch = (String)this.callGetValueFromView();
		for(SystemUnderTest item : listUnderTest)
		{
			String oneItem = item.getName().toLowerCase();
			if(oneItem.indexOf(valSearch) != -1)
				list.add(item);
		}
		return list;
	}
}
