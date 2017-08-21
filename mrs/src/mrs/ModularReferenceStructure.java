/**
 */
package mrs;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;
import org.modelversioning.emfprofile.Profile;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Modular Reference Structure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link mrs.ModularReferenceStructure#getLayers <em>Layers</em>}</li>
 *   <li>{@link mrs.ModularReferenceStructure#getLoadedProfiles <em>Loaded Profiles</em>}</li>
 * </ul>
 *
 * @see mrs.MrsPackage#getModularReferenceStructure()
 * @model
 * @generated
 */
public interface ModularReferenceStructure extends EObject {
	/**
     * Returns the value of the '<em><b>Layers</b></em>' containment reference list.
     * The list contents are of type {@link mrs.Layer}.
     * It is bidirectional and its opposite is '{@link mrs.Layer#getModularReferenceStructure <em>Modular Reference Structure</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Layers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Layers</em>' containment reference list.
     * @see mrs.MrsPackage#getModularReferenceStructure_Layers()
     * @see mrs.Layer#getModularReferenceStructure
     * @model opposite="modularReferenceStructure" containment="true" required="true"
     * @generated
     */
	EList<Layer> getLayers();

    /**
     * Returns the value of the '<em><b>Loaded Profiles</b></em>' reference list.
     * The list contents are of type {@link org.modelversioning.emfprofile.Profile}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Loaded Profiles</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Loaded Profiles</em>' reference list.
     * @see mrs.MrsPackage#getModularReferenceStructure_LoadedProfiles()
     * @model
     * @generated
     */
    EList<Profile> getLoadedProfiles();

} // ModularReferenceStructure
