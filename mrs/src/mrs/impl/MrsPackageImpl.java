/**
 */
package mrs.impl;

import mrs.Layer;
import mrs.LayerElement;
import mrs.Metamodel;
import mrs.ModularReferenceStructure;
import mrs.MrsFactory;
import mrs.MrsPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.modelversioning.emfprofile.EMFProfilePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MrsPackageImpl extends EPackageImpl implements MrsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modularReferenceStructureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass layerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass metamodelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass layerElementEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see mrs.MrsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private MrsPackageImpl() {
		super(eNS_URI, MrsFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link MrsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static MrsPackage init() {
		if (isInited) return (MrsPackage)EPackage.Registry.INSTANCE.getEPackage(MrsPackage.eNS_URI);

		// Obtain or create and register package
		MrsPackageImpl theMrsPackage = (MrsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof MrsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new MrsPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();
		EMFProfilePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theMrsPackage.createPackageContents();

		// Initialize created meta-data
		theMrsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theMrsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(MrsPackage.eNS_URI, theMrsPackage);
		return theMrsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModularReferenceStructure() {
		return modularReferenceStructureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModularReferenceStructure_Layers() {
		return (EReference)modularReferenceStructureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getModularReferenceStructure_LoadedProfiles() {
		return (EReference)modularReferenceStructureEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLayer() {
		return layerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLayer_Name() {
		return (EAttribute)layerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLayer_ModularReferenceStructure() {
		return (EReference)layerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLayer_LayerElements() {
		return (EReference)layerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMetamodel() {
		return metamodelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMetamodel_MainPackage() {
		return (EReference)metamodelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getMetamodel_Name() {
		return (EAttribute)metamodelEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLayerElement() {
		return layerElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLayerElement_Layer() {
		return (EReference)layerElementEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MrsFactory getMrsFactory() {
		return (MrsFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		modularReferenceStructureEClass = createEClass(MODULAR_REFERENCE_STRUCTURE);
		createEReference(modularReferenceStructureEClass, MODULAR_REFERENCE_STRUCTURE__LAYERS);
		createEReference(modularReferenceStructureEClass, MODULAR_REFERENCE_STRUCTURE__LOADED_PROFILES);

		layerEClass = createEClass(LAYER);
		createEAttribute(layerEClass, LAYER__NAME);
		createEReference(layerEClass, LAYER__MODULAR_REFERENCE_STRUCTURE);
		createEReference(layerEClass, LAYER__LAYER_ELEMENTS);

		metamodelEClass = createEClass(METAMODEL);
		createEReference(metamodelEClass, METAMODEL__MAIN_PACKAGE);
		createEAttribute(metamodelEClass, METAMODEL__NAME);

		layerElementEClass = createEClass(LAYER_ELEMENT);
		createEReference(layerElementEClass, LAYER_ELEMENT__LAYER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		EMFProfilePackage theEMFProfilePackage = (EMFProfilePackage)EPackage.Registry.INSTANCE.getEPackage(EMFProfilePackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		metamodelEClass.getESuperTypes().add(this.getLayerElement());

		// Initialize classes, features, and operations; add parameters
		initEClass(modularReferenceStructureEClass, ModularReferenceStructure.class, "ModularReferenceStructure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getModularReferenceStructure_Layers(), this.getLayer(), this.getLayer_ModularReferenceStructure(), "layers", null, 1, -1, ModularReferenceStructure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModularReferenceStructure_LoadedProfiles(), theEMFProfilePackage.getProfile(), null, "loadedProfiles", null, 0, -1, ModularReferenceStructure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(layerEClass, Layer.class, "Layer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLayer_Name(), theEcorePackage.getEString(), "name", null, 0, 1, Layer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLayer_ModularReferenceStructure(), this.getModularReferenceStructure(), this.getModularReferenceStructure_Layers(), "modularReferenceStructure", null, 1, 1, Layer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLayer_LayerElements(), this.getLayerElement(), this.getLayerElement_Layer(), "layerElements", null, 0, -1, Layer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(metamodelEClass, Metamodel.class, "Metamodel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMetamodel_MainPackage(), theEcorePackage.getEPackage(), null, "mainPackage", null, 1, 1, Metamodel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetamodel_Name(), theEcorePackage.getEString(), "name", "aMetamodel", 1, 1, Metamodel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(layerElementEClass, LayerElement.class, "LayerElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLayerElement_Layer(), this.getLayer(), this.getLayer_LayerElements(), "layer", null, 1, 1, LayerElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //MrsPackageImpl
