/**
 */
package mrs.tests;

import junit.textui.TestRunner;

import mrs.Metamodel;
import mrs.MrsFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Metamodel</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class MetamodelTest extends LayerElementTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(MetamodelTest.class);
	}

	/**
	 * Constructs a new Metamodel test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetamodelTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Metamodel test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Metamodel getFixture() {
		return (Metamodel)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(MrsFactory.eINSTANCE.createMetamodel());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //MetamodelTest
