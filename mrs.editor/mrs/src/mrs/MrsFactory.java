/**
 */
package mrs;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see mrs.MrsPackage
 * @generated
 */
public interface MrsFactory extends EFactory {
	/**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	MrsFactory eINSTANCE = mrs.impl.MrsFactoryImpl.init();

	/**
     * Returns a new object of class '<em>Modular Reference Structure</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Modular Reference Structure</em>'.
     * @generated
     */
	ModularReferenceStructure createModularReferenceStructure();

	/**
     * Returns a new object of class '<em>Layer</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Layer</em>'.
     * @generated
     */
	Layer createLayer();

	/**
     * Returns a new object of class '<em>Metamodel</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Metamodel</em>'.
     * @generated
     */
	Metamodel createMetamodel();

	/**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	MrsPackage getMrsPackage();

} //MrsFactory
