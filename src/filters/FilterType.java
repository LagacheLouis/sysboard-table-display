package filters;

import java.util.Collection;
import java.util.HashSet;

import cern.mpe.systems.core.domain.SystemUnderTest;

public class FilterType extends Filter{

	@Override
	public Collection<SystemUnderTest> filter(Collection<SystemUnderTest> listUnderTest) {
		Collection<SystemUnderTest> list = new HashSet<>();
		for(SystemUnderTest item : listUnderTest)
		{
			String itemstr = item.getClass().getSimpleName();
			if(itemstr.equals(this.callGetValueFromView()) || this.callGetValueFromView().equals("-"))
			{
				list.add(item);
			}
		}
		return list;
	}

}
