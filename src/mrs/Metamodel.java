/**
 */
package mrs;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Metamodel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link mrs.Metamodel#getMainPackage <em>Main Package</em>}</li>
 *   <li>{@link mrs.Metamodel#getVisibleEClassifiers <em>Visible EClassifiers</em>}</li>
 *   <li>{@link mrs.Metamodel#getVisibleEPackages <em>Visible EPackages</em>}</li>
 *   <li>{@link mrs.Metamodel#getLayer <em>Layer</em>}</li>
 * </ul>
 *
 * @see mrs.MrsPackage#getMetamodel()
 * @model
 * @generated
 */
public interface Metamodel extends EObject {
	/**
	 * Returns the value of the '<em><b>Main Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Main Package</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Main Package</em>' reference.
	 * @see #setMainPackage(EPackage)
	 * @see mrs.MrsPackage#getMetamodel_MainPackage()
	 * @model required="true"
	 * @generated
	 */
	EPackage getMainPackage();

	/**
	 * Sets the value of the '{@link mrs.Metamodel#getMainPackage <em>Main Package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Main Package</em>' reference.
	 * @see #getMainPackage()
	 * @generated
	 */
	void setMainPackage(EPackage value);

	/**
	 * Returns the value of the '<em><b>Visible EClassifiers</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EClassifier}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Visible EClassifiers</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Visible EClassifiers</em>' reference list.
	 * @see mrs.MrsPackage#getMetamodel_VisibleEClassifiers()
	 * @model
	 * @generated
	 */
	EList<EClassifier> getVisibleEClassifiers();

	/**
	 * Returns the value of the '<em><b>Visible EPackages</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EPackage}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Visible EPackages</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Visible EPackages</em>' reference list.
	 * @see mrs.MrsPackage#getMetamodel_VisibleEPackages()
	 * @model
	 * @generated
	 */
	EList<EPackage> getVisibleEPackages();

	/**
	 * Returns the value of the '<em><b>Layer</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link mrs.Layer#getMetamodels <em>Metamodels</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Layer</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Layer</em>' container reference.
	 * @see #setLayer(Layer)
	 * @see mrs.MrsPackage#getMetamodel_Layer()
	 * @see mrs.Layer#getMetamodels
	 * @model opposite="metamodels" required="true" transient="false"
	 * @generated
	 */
	Layer getLayer();

	/**
	 * Sets the value of the '{@link mrs.Metamodel#getLayer <em>Layer</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Layer</em>' container reference.
	 * @see #getLayer()
	 * @generated
	 */
	void setLayer(Layer value);

} // Metamodel
