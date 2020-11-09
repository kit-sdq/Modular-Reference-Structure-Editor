/**
 */
package edu.kit.kastel.sdq.mrs;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see edu.kit.kastel.sdq.mrs.MrsFactory
 * @model kind="package"
 * @generated
 */
public interface MrsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "edu.kit.kastel.sdq.mrs";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.example.org/edu.kit.kastel.sdq.mrs";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "edu.kit.kastel.sdq.mrs";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MrsPackage eINSTANCE = edu.kit.kastel.sdq.mrs.impl.MrsPackageImpl.init();

	/**
	 * The meta object id for the '{@link edu.kit.kastel.sdq.mrs.impl.ModularReferenceStructureImpl <em>Modular Reference Structure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.kastel.sdq.mrs.impl.ModularReferenceStructureImpl
	 * @see edu.kit.kastel.sdq.mrs.impl.MrsPackageImpl#getModularReferenceStructure()
	 * @generated
	 */
	int MODULAR_REFERENCE_STRUCTURE = 0;

	/**
	 * The feature id for the '<em><b>Layers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULAR_REFERENCE_STRUCTURE__LAYERS = 0;

	/**
	 * The feature id for the '<em><b>Loaded Profiles</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULAR_REFERENCE_STRUCTURE__LOADED_PROFILES = 1;

	/**
	 * The number of structural features of the '<em>Modular Reference Structure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULAR_REFERENCE_STRUCTURE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Modular Reference Structure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULAR_REFERENCE_STRUCTURE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link edu.kit.kastel.sdq.mrs.impl.LayerImpl <em>Layer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.kastel.sdq.mrs.impl.LayerImpl
	 * @see edu.kit.kastel.sdq.mrs.impl.MrsPackageImpl#getLayer()
	 * @generated
	 */
	int LAYER = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER__NAME = 0;

	/**
	 * The feature id for the '<em><b>Metamodels</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER__METAMODELS = 1;

	/**
	 * The feature id for the '<em><b>Modular Reference Structure</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER__MODULAR_REFERENCE_STRUCTURE = 2;

	/**
	 * The number of structural features of the '<em>Layer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Layer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link edu.kit.kastel.sdq.mrs.impl.MetamodelImpl <em>Metamodel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.kastel.sdq.mrs.impl.MetamodelImpl
	 * @see edu.kit.kastel.sdq.mrs.impl.MrsPackageImpl#getMetamodel()
	 * @generated
	 */
	int METAMODEL = 2;

	/**
	 * The feature id for the '<em><b>Main Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METAMODEL__MAIN_PACKAGE = 0;

	/**
	 * The feature id for the '<em><b>Layer</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METAMODEL__LAYER = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METAMODEL__NAME = 2;

	/**
	 * The number of structural features of the '<em>Metamodel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METAMODEL_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Metamodel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METAMODEL_OPERATION_COUNT = 0;

	/**
	 * Returns the meta object for class '{@link edu.kit.kastel.sdq.mrs.ModularReferenceStructure <em>Modular Reference Structure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Modular Reference Structure</em>'.
	 * @see edu.kit.kastel.sdq.mrs.ModularReferenceStructure
	 * @generated
	 */
	EClass getModularReferenceStructure();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.kit.kastel.sdq.mrs.ModularReferenceStructure#getLayers <em>Layers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Layers</em>'.
	 * @see edu.kit.kastel.sdq.mrs.ModularReferenceStructure#getLayers()
	 * @see #getModularReferenceStructure()
	 * @generated
	 */
	EReference getModularReferenceStructure_Layers();

	/**
	 * Returns the meta object for the reference list '{@link edu.kit.kastel.sdq.mrs.ModularReferenceStructure#getLoadedProfiles <em>Loaded Profiles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Loaded Profiles</em>'.
	 * @see edu.kit.kastel.sdq.mrs.ModularReferenceStructure#getLoadedProfiles()
	 * @see #getModularReferenceStructure()
	 * @generated
	 */
	EReference getModularReferenceStructure_LoadedProfiles();

	/**
	 * Returns the meta object for class '{@link edu.kit.kastel.sdq.mrs.Layer <em>Layer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Layer</em>'.
	 * @see edu.kit.kastel.sdq.mrs.Layer
	 * @generated
	 */
	EClass getLayer();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.kastel.sdq.mrs.Layer#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see edu.kit.kastel.sdq.mrs.Layer#getName()
	 * @see #getLayer()
	 * @generated
	 */
	EAttribute getLayer_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.kit.kastel.sdq.mrs.Layer#getMetamodels <em>Metamodels</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Metamodels</em>'.
	 * @see edu.kit.kastel.sdq.mrs.Layer#getMetamodels()
	 * @see #getLayer()
	 * @generated
	 */
	EReference getLayer_Metamodels();

	/**
	 * Returns the meta object for the container reference '{@link edu.kit.kastel.sdq.mrs.Layer#getModularReferenceStructure <em>Modular Reference Structure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Modular Reference Structure</em>'.
	 * @see edu.kit.kastel.sdq.mrs.Layer#getModularReferenceStructure()
	 * @see #getLayer()
	 * @generated
	 */
	EReference getLayer_ModularReferenceStructure();

	/**
	 * Returns the meta object for class '{@link edu.kit.kastel.sdq.mrs.Metamodel <em>Metamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Metamodel</em>'.
	 * @see edu.kit.kastel.sdq.mrs.Metamodel
	 * @generated
	 */
	EClass getMetamodel();

	/**
	 * Returns the meta object for the reference '{@link edu.kit.kastel.sdq.mrs.Metamodel#getMainPackage <em>Main Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Main Package</em>'.
	 * @see edu.kit.kastel.sdq.mrs.Metamodel#getMainPackage()
	 * @see #getMetamodel()
	 * @generated
	 */
	EReference getMetamodel_MainPackage();

	/**
	 * Returns the meta object for the container reference '{@link edu.kit.kastel.sdq.mrs.Metamodel#getLayer <em>Layer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Layer</em>'.
	 * @see edu.kit.kastel.sdq.mrs.Metamodel#getLayer()
	 * @see #getMetamodel()
	 * @generated
	 */
	EReference getMetamodel_Layer();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.kastel.sdq.mrs.Metamodel#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see edu.kit.kastel.sdq.mrs.Metamodel#getName()
	 * @see #getMetamodel()
	 * @generated
	 */
	EAttribute getMetamodel_Name();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	MrsFactory getMrsFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link edu.kit.kastel.sdq.mrs.impl.ModularReferenceStructureImpl <em>Modular Reference Structure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.kastel.sdq.mrs.impl.ModularReferenceStructureImpl
		 * @see edu.kit.kastel.sdq.mrs.impl.MrsPackageImpl#getModularReferenceStructure()
		 * @generated
		 */
		EClass MODULAR_REFERENCE_STRUCTURE = eINSTANCE.getModularReferenceStructure();

		/**
		 * The meta object literal for the '<em><b>Layers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODULAR_REFERENCE_STRUCTURE__LAYERS = eINSTANCE.getModularReferenceStructure_Layers();

		/**
		 * The meta object literal for the '<em><b>Loaded Profiles</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODULAR_REFERENCE_STRUCTURE__LOADED_PROFILES = eINSTANCE.getModularReferenceStructure_LoadedProfiles();

		/**
		 * The meta object literal for the '{@link edu.kit.kastel.sdq.mrs.impl.LayerImpl <em>Layer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.kastel.sdq.mrs.impl.LayerImpl
		 * @see edu.kit.kastel.sdq.mrs.impl.MrsPackageImpl#getLayer()
		 * @generated
		 */
		EClass LAYER = eINSTANCE.getLayer();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LAYER__NAME = eINSTANCE.getLayer_Name();

		/**
		 * The meta object literal for the '<em><b>Metamodels</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LAYER__METAMODELS = eINSTANCE.getLayer_Metamodels();

		/**
		 * The meta object literal for the '<em><b>Modular Reference Structure</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LAYER__MODULAR_REFERENCE_STRUCTURE = eINSTANCE.getLayer_ModularReferenceStructure();

		/**
		 * The meta object literal for the '{@link edu.kit.kastel.sdq.mrs.impl.MetamodelImpl <em>Metamodel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.kastel.sdq.mrs.impl.MetamodelImpl
		 * @see edu.kit.kastel.sdq.mrs.impl.MrsPackageImpl#getMetamodel()
		 * @generated
		 */
		EClass METAMODEL = eINSTANCE.getMetamodel();

		/**
		 * The meta object literal for the '<em><b>Main Package</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METAMODEL__MAIN_PACKAGE = eINSTANCE.getMetamodel_MainPackage();

		/**
		 * The meta object literal for the '<em><b>Layer</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METAMODEL__LAYER = eINSTANCE.getMetamodel_Layer();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METAMODEL__NAME = eINSTANCE.getMetamodel_Name();

	}

} //MrsPackage
