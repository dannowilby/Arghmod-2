package wilby.api;

public enum ArghTier 
{
	// 65536 524288 4194304
	TIER1("Enforced", 64000), TIER2("Hardened", 525288), TIER3("Resonant", 4194304);
	
	private String name;
	private int max;
	
	ArghTier(String name, int max)
	{
		this.name = name;
		this.max = max;
	}
	
	public int getMaxDamage()
	{
		return this.max;
	}
	
	public String getDisplayName()
	{
		return this.name;
	}
	
}
