package org.drumm.mosaic.kids.ratios.store;

public class KidsRoomStoreFactory {
	public KidsRoomStoreFactory(Object properties){
		System.out.println(properties.getClass().getCanonicalName());
	}
	
	public IKidsRoomStore getKidsRoomStore (){
		return null;
	}
}
