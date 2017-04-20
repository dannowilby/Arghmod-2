package wilby.argh.common.item;

import wilby.api.ArghTier;

public class ItemEnforcedJetpack extends ItemJetpack
{

	public ItemEnforcedJetpack(String name, ArghTier t) 
	{
		super(name, t, t.getMaxDamage());
	}

}
