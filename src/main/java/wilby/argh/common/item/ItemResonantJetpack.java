package wilby.argh.common.item;

import wilby.api.ArghTier;

public class ItemResonantJetpack extends ItemJetpack
{

	public ItemResonantJetpack(String name, ArghTier t) 
	{
		super(name, t, t.getMaxDamage());
	}

}
