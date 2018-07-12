package mrs.featuremodel.custom.util;

import java.util.Optional;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.modelversioning.emfprofileapplication.StereotypeApplication;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;

import mrs.Metamodel;

public class MetamodelURLUtil {
	public static final String METAMODEL_URL_STEREOTYPE = "PluginURL";
	public static final String URL_TAGGED_VALUE = "url";
	
	public static void applyStereotypeMetamodelURL(Metamodel metamodel) {
		StereotypeAPI.applyStereotype(metamodel, METAMODEL_URL_STEREOTYPE);
	}
	
	public static boolean isStereotypeMetamodelURLApplied(Metamodel metamodel) {
		return StereotypeAPI.isStereotypeApplied(metamodel, METAMODEL_URL_STEREOTYPE);
	}
	
	public static StereotypeApplication getStereotypeApplicationMetamodelURL(Metamodel metamodel) {
		Optional<StereotypeApplication> stereotypeApplicationOptional = StereotypeAPI
				.getStereotypeApplications(metamodel, METAMODEL_URL_STEREOTYPE).stream().findFirst();
		return stereotypeApplicationOptional.orElse(null);
	}
	
	public static void setURL(Metamodel metamodel, String url) {
		if (!isStereotypeMetamodelURLApplied(metamodel))
			applyStereotypeMetamodelURL(metamodel);
		
		StereotypeApplication stereotypeApplication = getStereotypeApplicationMetamodelURL(metamodel);
		EStructuralFeature taggedValue = stereotypeApplication.getStereotype().getTaggedValue(URL_TAGGED_VALUE);
		stereotypeApplication.eSet(taggedValue, url);
	}
	
	public static String getURL(Metamodel metamodel) {
		if (!isStereotypeMetamodelURLApplied(metamodel))
			return "";
		
		StereotypeApplication stereotypeApplication = getStereotypeApplicationMetamodelURL(metamodel);
		EStructuralFeature taggedValue = stereotypeApplication.getStereotype().getTaggedValue(URL_TAGGED_VALUE);
		
		Object url = stereotypeApplication.eGet(taggedValue);
		if (url == null) {
			return "";
		} else {
			return (String) url;
		}
	}

}
