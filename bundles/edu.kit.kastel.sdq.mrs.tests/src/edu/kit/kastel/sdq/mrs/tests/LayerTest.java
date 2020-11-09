/**
 */
package edu.kit.kastel.sdq.mrs.tests;

import edu.kit.kastel.sdq.mrs.Layer;
import edu.kit.kastel.sdq.mrs.MrsFactory;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Layer</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class LayerTest extends TestCase {

	/**
	 * The fixture for this Layer test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Layer fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(LayerTest.class);
	}

	/**
	 * Constructs a new Layer test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LayerTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Layer test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(Layer fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Layer test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Layer getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(MrsFactory.eINSTANCE.createLayer());
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

} //LayerTest
