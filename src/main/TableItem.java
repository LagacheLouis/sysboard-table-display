package main;

/**
 * A standard item for {@link TableView}
 * each property match to the {@link TableColumn}
 * @author Lagache
 *
 */
public class TableItem {

	public int nbProps;
	public String prop1;
	public String prop2;
	public String prop3;
	public String prop4;
	public String prop5;
	public String prop6;
	public String prop7;
	public String prop8;
	public String prop9;
	public String prop10;

	
	/**
	 * @param props
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	public TableItem(String props[]) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		super();

		this.nbProps = props.length;

		this.prop1 = null;
		this.prop2 = null;
		this.prop3 = null;
		this.prop4 = null;
		this.prop5 = null;
		this.prop6 = null;
		this.prop7 = null;
		this.prop8 = null;
		this.prop9 = null;
		this.prop10 = null;

		Class c = this.getClass();
		for(int i = 0;i < this.nbProps; i ++)
		{
			c.getField("prop" + (i+1)).set(this, props[i]);
		}


	}

	public int getNbProps() {
		return nbProps;
	}

	public String getProp1() {
		return prop1;
	}

	public String getProp2() {
		return prop2;
	}

	public String getProp3() {
		return prop3;
	}

	public String getProp4() {
		return prop4;
	}

	public String getProp5() {
		return prop5;
	}

	public String getProp6() {
		return prop6;
	}

	public String getProp7() {
		return prop7;
	}

	public String getProp8() {
		return prop8;
	}

	public String getProp9() {
		return prop9;
	}

	public String getProp10() {
		return prop10;
	}
}
