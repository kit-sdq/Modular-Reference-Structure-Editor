package mrs.design;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.modelversioning.emfprofileapplication.StereotypeApplication;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;

public class MDSDProfilesServices {

    public MDSDProfilesServices() {
    }
    
    /**
     * @see StereotypeAPI#getStereotypeApplications(EObject)
     */
    public Collection<StereotypeApplication> getStereotypeApplications(final EObject eObject) {
        return StereotypeAPI.getStereotypeApplications(eObject);
    }
    
    
    
    

}
