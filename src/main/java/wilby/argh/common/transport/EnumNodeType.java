package wilby.argh.common.transport;

public enum EnumNodeType
{
	ENERGY(0), ITEM(1), FLUID(2), INVALID(-1);
	
	int id;
	
	EnumNodeType(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return id;
	}
	
	public EnumNodeType getTypeFromId(int id)
	{
		if(id == 0)
			return this.ENERGY;
		if(id == 1)
			return this.ITEM;
		if(id == 2)
			return this.FLUID;
		else
			return this.INVALID;
	}
}