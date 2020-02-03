package logic.bean;

import java.util.List;

public class FiltersSet {
	
	// Question 1 flags
	private boolean locationSea;
	private boolean locationCity;
	private boolean locationMountain;
	
	// Question 2 flags
	private boolean activityTourism;
	private boolean activityAdventure;
	private boolean activityRelax;
	
	// Question 2.1 flags
	private boolean adventureSport;
	private boolean adventureDiscovery;
	
	// Question 2.2 flags
	private boolean relaxNature;
	private boolean relaxRomantic;
	private boolean relaxMeditation;
	
	// Question 3 flags
	private boolean attractionsArts;
	private boolean attractionsSports;
	private boolean attractionsNightlife;

	private int budget;
	
	// Question 5 answer
	private String locationPlacement; // only "USERCOUNTRY"/"USERCONTINENT"/"OTHER" values allowed
	private boolean familyTrip;
	
	// Question 7 tags
	private List<String> otherTags;
	
	// Getters methods
	
	public boolean isLocationSea() {
		return locationSea;
	}
	
	public boolean isLocationCity() {
		return locationCity;
	}
	
	public boolean isLocationMountain() {
		return locationMountain;
	}
	
	public boolean isActivityTourism() {
		return activityTourism;
	}
	
	public boolean isActivityAdventure() {
		return activityAdventure;
	}
	
	public boolean isActivityRelax() {
		return activityRelax;
	}
	
	public boolean isAdventureSport() {
		return adventureSport;
	}
	
	public boolean isAdventureDiscovery() {
		return adventureDiscovery;
	}
	
	public boolean isRelaxNature() {
		return relaxNature;
	}
	
	public boolean isRelaxRomantic() {
		return relaxRomantic;
	}
	
	public boolean isRelaxMeditation() {
		return relaxMeditation;
	}
	
	public boolean isAttractionsArts() {
		return attractionsArts;
	}
	
	public boolean isAttractionsSports() {
		return attractionsSports;
	}
	
	public boolean isAttractionsNightlife() {
		return attractionsNightlife;
	}
	
	public int getBudget() {
		return budget;
	}
	
	public String getLocationPlacement() {
		return locationPlacement;
	}
	
	public boolean isFamilyTrip() {
		return familyTrip;
	}
	
	public List<String> getOtherTags() {
		return otherTags;
	}

	
	// Setters methods
	
	public void setLocationSea(boolean locationSea) {
		this.locationSea = locationSea;
	}
	
	public void setLocationCity(boolean locationCity) {
		this.locationCity = locationCity;
	}
	
	public void setLocationMountain(boolean locationMountain) {
		this.locationMountain = locationMountain;
	}
	
	public void setActivityTourism(boolean activityTourism) {
		this.activityTourism = activityTourism;
	}
	
	public void setActivityAdventure(boolean activityAdventure) {
		this.activityAdventure = activityAdventure;
	}
	
	public void setActivityRelax(boolean activityRelax) {
		this.activityRelax = activityRelax;
	}
	
	public void setAdventureSport(boolean adventureSport) {
		this.adventureSport = adventureSport;
	}
	
	public void setAdventureDiscovery(boolean adventureDiscovery) {
		this.adventureDiscovery = adventureDiscovery;
	}
	
	public void setRelaxNature(boolean relaxNature) {
		this.relaxNature = relaxNature;
	}
	
	public void setRelaxRomantic(boolean relaxRomantic) {
		this.relaxRomantic = relaxRomantic;
	}
	
	public void setRelaxMeditation(boolean relaxMeditation) {
		this.relaxMeditation = relaxMeditation;
	}
	
	public void setAttractionsArts(boolean attractionsArts) {
		this.attractionsArts = attractionsArts;
	}
	
	public void setAttractionsSports(boolean attractionsSports) {
		this.attractionsSports = attractionsSports;
	}
	
	public void setAttractionsNightlife(boolean attractionsNightlife) {
		this.attractionsNightlife = attractionsNightlife;
	}
	
	public void setBudget(int budget) {
		this.budget = budget;
	}
	
	public void setLocationPlacement(String locationPlacement) {
		if(locationPlacement.equals("USERCOUNTRY") ||
				locationPlacement.equals("USERCONTINENT") ||
				locationPlacement.equals("OTHER"))
			this.locationPlacement = locationPlacement;
		else
			throw new IllegalArgumentException();

	}
	
	public void setFamilyTrip(boolean familyTrip) {
		this.familyTrip = familyTrip;
	}

	public void setOtherTags(List<String> otherTags) {
		if(otherTags.equals("SPA") ||
				otherTags.equals("FORGROUPS") ||
				otherTags.equals("MONUMENTS") ||
				otherTags.equals("LAKE") ||
				otherTags.equals("SAFARI") ||
				otherTags.equals("") ||
				otherTags.equals(""))
			this.otherTags = otherTags;
		else
			throw new IllegalArgumentException();
	}

}