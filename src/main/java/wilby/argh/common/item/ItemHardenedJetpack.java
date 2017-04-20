package wilby.argh.common.item;

import wilby.api.ArghTier;

public class ItemHardenedJetpack extends ItemJetpack
{

	public ItemHardenedJetpack(String name, ArghTier t) 
	{
		super(name, t, t.getMaxDamage());
	}

}
