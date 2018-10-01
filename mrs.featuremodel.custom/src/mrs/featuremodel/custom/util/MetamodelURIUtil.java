package mrs.featuremodel.custom.util;

import java.util.Optional;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.modelversioning.emfprofileapplication.StereotypeApplication;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;

import mrs.Metamodel;

public class MetamodelURIUtil {
	public static final String METAMODEL_URI_STEREOTYPE = "PluginURL";
	public static final String URI_TAGGED_VALUE = "url";
	
	public static void applyStereotypeMetamodelURL(Metamodel metamodel) {
		StereotypeAPI.applyStereotype(metamodel, METAMODEL_URI_STEREOTYPE);
	}
	
	public static boolean isStereotypeMetamodelURIApplied(Metamodel metamodel) {
		return StereotypeAPI.isStereotypeApplied(metamodel, METAMODEL_URI_STEREOTYPE);
	}
	
	public static StereotypeApplication getStereotypeApplicationMetamodelURI(Metamodel metamodel) {
		Optional<StereotypeApplication> stereotypeApplicationOptional = StereotypeAPI
				.getStereotypeApplications(metamodel, METAMODEL_URI_STEREOTYPE).stream().findFirst();
		return stereotypeApplicationOptional.orElse(null);
	}
	
	public static void setURI(Metamodel metamodel, String url) {
		if (!isStereotypeMetamodelURIApplied(metamodel))
			applyStereotypeMetamodelURL(metamodel);
		
		StereotypeApplication stereotypeApplication = getStereotypeApplicationMetamodelURI(metamodel);
		EStructuralFeature taggedValue = stereotypeApplication.getStereotype().getTaggedValue(URI_TAGGED_VALUE);
		stereotypeApplication.eSet(taggedValue, url);
	}
	
	public static String getURI(Metamodel metamodel) {
		if (!isStereotypeMetamodelURIApplied(metamodel))
			return "";
		
		StereotypeApplication stereotypeApplication = getStereotypeApplicationMetamodelURI(metamodel);
		EStructuralFeature taggedValue = stereotypeApplication.getStereotype().getTaggedValue(URI_TAGGED_VALUE);
		
		Object uri = stereotypeApplication.eGet(taggedValue);
		if (uri == null) {
			return "";
		} else {
			return (String) uri;
		}
	}

}
