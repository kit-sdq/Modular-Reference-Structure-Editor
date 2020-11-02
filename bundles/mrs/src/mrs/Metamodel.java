/**
 */
package mrs;

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
 *   <li>{@link mrs.Metamodel#getLayer <em>Layer</em>}</li>
 *   <li>{@link mrs.Metamodel#getName <em>Name</em>}</li>
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

    /**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * The default value is <code>"aMetamodel"</code>.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see mrs.MrsPackage#getMetamodel_Name()
	 * @model default="aMetamodel" required="true"
	 * @generated
	 */
    String getName();

    /**
	 * Sets the value of the '{@link mrs.Metamodel#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
    void setName(String value);

} // Metamodel
