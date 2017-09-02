/**
 */
package mrs.impl;

import java.util.Collection;

import mrs.Layer;
import mrs.Metamodel;
import mrs.MrsPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Metamodel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link mrs.impl.MetamodelImpl#getMainPackage <em>Main Package</em>}</li>
 *   <li>{@link mrs.impl.MetamodelImpl#getVisibleEClassifiers <em>Visible EClassifiers</em>}</li>
 *   <li>{@link mrs.impl.MetamodelImpl#getVisibleEPackages <em>Visible EPackages</em>}</li>
 *   <li>{@link mrs.impl.MetamodelImpl#getLayer <em>Layer</em>}</li>
 *   <li>{@link mrs.impl.MetamodelImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MetamodelImpl extends MinimalEObjectImpl.Container implements Metamodel {
	/**
     * The cached value of the '{@link #getMainPackage() <em>Main Package</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMainPackage()
     * @generated
     * @ordered
     */
	protected EPackage mainPackage;

	/**
     * The cached value of the '{@link #getVisibleEClassifiers() <em>Visible EClassifiers</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getVisibleEClassifiers()
     * @generated
     * @ordered
     */
	protected EList<EClassifier> visibleEClassifiers;

	/**
     * The cached value of the '{@link #getVisibleEPackages() <em>Visible EPackages</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getVisibleEPackages()
     * @generated
     * @ordered
     */
	protected EList<EPackage> visibleEPackages;

	/**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = "aMetamodel";

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected MetamodelImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return MrsPackage.Literals.METAMODEL;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EPackage getMainPackage() {
        if (mainPackage != null && mainPackage.eIsProxy()) {
            InternalEObject oldMainPackage = (InternalEObject)mainPackage;
            mainPackage = (EPackage)eResolveProxy(oldMainPackage);
            if (mainPackage != oldMainPackage) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, MrsPackage.METAMODEL__MAIN_PACKAGE, oldMainPackage, mainPackage));
            }
        }
        return mainPackage;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EPackage basicGetMainPackage() {
        return mainPackage;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setMainPackage(EPackage newMainPackage) {
        EPackage oldMainPackage = mainPackage;
        mainPackage = newMainPackage;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MrsPackage.METAMODEL__MAIN_PACKAGE, oldMainPackage, mainPackage));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<EClassifier> getVisibleEClassifiers() {
        if (visibleEClassifiers == null) {
            visibleEClassifiers = new EObjectResolvingEList<EClassifier>(EClassifier.class, this, MrsPackage.METAMODEL__VISIBLE_ECLASSIFIERS);
        }
        return visibleEClassifiers;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<EPackage> getVisibleEPackages() {
        if (visibleEPackages == null) {
            visibleEPackages = new EObjectResolvingEList<EPackage>(EPackage.class, this, MrsPackage.METAMODEL__VISIBLE_EPACKAGES);
        }
        return visibleEPackages;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Layer getLayer() {
        if (eContainerFeatureID() != MrsPackage.METAMODEL__LAYER) return null;
        return (Layer)eInternalContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetLayer(Layer newLayer, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newLayer, MrsPackage.METAMODEL__LAYER, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setLayer(Layer newLayer) {
        if (newLayer != eInternalContainer() || (eContainerFeatureID() != MrsPackage.METAMODEL__LAYER && newLayer != null)) {
            if (EcoreUtil.isAncestor(this, newLayer))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newLayer != null)
                msgs = ((InternalEObject)newLayer).eInverseAdd(this, MrsPackage.LAYER__METAMODELS, Layer.class, msgs);
            msgs = basicSetLayer(newLayer, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MrsPackage.METAMODEL__LAYER, newLayer, newLayer));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MrsPackage.METAMODEL__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case MrsPackage.METAMODEL__LAYER:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetLayer((Layer)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case MrsPackage.METAMODEL__LAYER:
                return basicSetLayer(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID()) {
            case MrsPackage.METAMODEL__LAYER:
                return eInternalContainer().eInverseRemove(this, MrsPackage.LAYER__METAMODELS, Layer.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case MrsPackage.METAMODEL__MAIN_PACKAGE:
                if (resolve) return getMainPackage();
                return basicGetMainPackage();
            case MrsPackage.METAMODEL__VISIBLE_ECLASSIFIERS:
                return getVisibleEClassifiers();
            case MrsPackage.METAMODEL__VISIBLE_EPACKAGES:
                return getVisibleEPackages();
            case MrsPackage.METAMODEL__LAYER:
                return getLayer();
            case MrsPackage.METAMODEL__NAME:
                return getName();
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case MrsPackage.METAMODEL__MAIN_PACKAGE:
                setMainPackage((EPackage)newValue);
                return;
            case MrsPackage.METAMODEL__VISIBLE_ECLASSIFIERS:
                getVisibleEClassifiers().clear();
                getVisibleEClassifiers().addAll((Collection<? extends EClassifier>)newValue);
                return;
            case MrsPackage.METAMODEL__VISIBLE_EPACKAGES:
                getVisibleEPackages().clear();
                getVisibleEPackages().addAll((Collection<? extends EPackage>)newValue);
                return;
            case MrsPackage.METAMODEL__LAYER:
                setLayer((Layer)newValue);
                return;
            case MrsPackage.METAMODEL__NAME:
                setName((String)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void eUnset(int featureID) {
        switch (featureID) {
            case MrsPackage.METAMODEL__MAIN_PACKAGE:
                setMainPackage((EPackage)null);
                return;
            case MrsPackage.METAMODEL__VISIBLE_ECLASSIFIERS:
                getVisibleEClassifiers().clear();
                return;
            case MrsPackage.METAMODEL__VISIBLE_EPACKAGES:
                getVisibleEPackages().clear();
                return;
            case MrsPackage.METAMODEL__LAYER:
                setLayer((Layer)null);
                return;
            case MrsPackage.METAMODEL__NAME:
                setName(NAME_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean eIsSet(int featureID) {
        switch (featureID) {
            case MrsPackage.METAMODEL__MAIN_PACKAGE:
                return mainPackage != null;
            case MrsPackage.METAMODEL__VISIBLE_ECLASSIFIERS:
                return visibleEClassifiers != null && !visibleEClassifiers.isEmpty();
            case MrsPackage.METAMODEL__VISIBLE_EPACKAGES:
                return visibleEPackages != null && !visibleEPackages.isEmpty();
            case MrsPackage.METAMODEL__LAYER:
                return getLayer() != null;
            case MrsPackage.METAMODEL__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (name: ");
        result.append(name);
        result.append(')');
        return result.toString();
    }

} //MetamodelImpl
