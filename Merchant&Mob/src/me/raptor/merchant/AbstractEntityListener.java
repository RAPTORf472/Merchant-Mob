package me.raptor.merchant;

import java.io.File;

import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;

import com.comphenix.protocol.wrappers.WrappedGameProfile;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import me.libraryaddict.disguise.utilities.SkinUtils;
import me.libraryaddict.disguise.utilities.SkinUtils.ModelType;
import me.libraryaddict.disguise.utilities.SkinUtils.SkinCallback;
import me.libraryaddict.disguise.utilities.translations.LibsMsg;

public abstract class AbstractEntityListener implements Listener {
	
	public boolean checkName(LivingEntity l, String name) {
		if (l.getCustomName() == null) return false;
		if (l.getCustomName().contains(name)) return true;
		else return false;
	}
	
	public boolean checkKey(LivingEntity l, String key, NamespacedKey nkey) {
		if (l.getPersistentDataContainer().get(nkey, PersistentDataType.STRING) == null) return false;
		if (l.getPersistentDataContainer().get(nkey, PersistentDataType.STRING).equals(key)) return true;
		else return false;
	}
	
	public void setKey(LivingEntity l, String key, NamespacedKey nkey) {
		l.getPersistentDataContainer().set(nkey, PersistentDataType.STRING, key);
	}
	
	public void setSkin(LivingEntity l, File file, String customName) { //set skin using LibDisguiseAPI
		PlayerDisguise mobDisguise = new PlayerDisguise(customName);
		SkinUtils.handleFile(file, ModelType.NORMAL, new SkinCallback() {

			@Override
			public void onError(LibsMsg arg0, Object... arg1) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onInfo(LibsMsg arg0, Object... arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(WrappedGameProfile arg0) {
				// TODO Auto-generated method stub
				mobDisguise.setEntity(l);
				mobDisguise.setSkin(arg0);
				mobDisguise.startDisguise();
				l.setCustomName(customName);
				l.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.7);
			}
			
		});
	}
	
	public void disguiseMob(LivingEntity l, DisguiseType d) { //LibDisguise API
		MobDisguise disguise = new MobDisguise(d);
		disguise.setEntity(l);
		disguise.startDisguise();
	}
	
	public void disguisePlayer(LivingEntity l, String skin) { // LibDisguise API
		PlayerDisguise disguise = new PlayerDisguise(skin);
		disguise.setEntity(l);
		disguise.startDisguise();
	}
	
}
