/**
 */
package edu.kit.kastel.sdq.mrs;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Layer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link edu.kit.kastel.sdq.mrs.Layer#getName <em>Name</em>}</li>
 *   <li>{@link edu.kit.kastel.sdq.mrs.Layer#getMetamodels <em>Metamodels</em>}</li>
 *   <li>{@link edu.kit.kastel.sdq.mrs.Layer#getModularReferenceStructure <em>Modular Reference Structure</em>}</li>
 * </ul>
 *
 * @see edu.kit.kastel.sdq.mrs.MrsPackage#getLayer()
 * @model
 * @generated
 */
public interface Layer extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see edu.kit.kastel.sdq.mrs.MrsPackage#getLayer_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link edu.kit.kastel.sdq.mrs.Layer#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Metamodels</b></em>' containment reference list.
	 * The list contents are of type {@link edu.kit.kastel.sdq.mrs.Metamodel}.
	 * It is bidirectional and its opposite is '{@link edu.kit.kastel.sdq.mrs.Metamodel#getLayer <em>Layer</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metamodels</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Metamodels</em>' containment reference list.
	 * @see edu.kit.kastel.sdq.mrs.MrsPackage#getLayer_Metamodels()
	 * @see edu.kit.kastel.sdq.mrs.Metamodel#getLayer
	 * @model opposite="layer" containment="true"
	 * @generated
	 */
	EList<Metamodel> getMetamodels();

	/**
	 * Returns the value of the '<em><b>Modular Reference Structure</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.kit.kastel.sdq.mrs.ModularReferenceStructure#getLayers <em>Layers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modular Reference Structure</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modular Reference Structure</em>' container reference.
	 * @see #setModularReferenceStructure(ModularReferenceStructure)
	 * @see edu.kit.kastel.sdq.mrs.MrsPackage#getLayer_ModularReferenceStructure()
	 * @see edu.kit.kastel.sdq.mrs.ModularReferenceStructure#getLayers
	 * @model opposite="layers" required="true" transient="false"
	 * @generated
	 */
	ModularReferenceStructure getModularReferenceStructure();

	/**
	 * Sets the value of the '{@link edu.kit.kastel.sdq.mrs.Layer#getModularReferenceStructure <em>Modular Reference Structure</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Modular Reference Structure</em>' container reference.
	 * @see #getModularReferenceStructure()
	 * @generated
	 */
	void setModularReferenceStructure(ModularReferenceStructure value);

} // Layer
