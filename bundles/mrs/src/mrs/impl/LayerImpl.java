/**
 */
package mrs.impl;

import java.util.Collection;

import mrs.Layer;
import mrs.Metamodel;
import mrs.ModularReferenceStructure;
import mrs.MrsPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Layer</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link mrs.impl.LayerImpl#getName <em>Name</em>}</li>
 *   <li>{@link mrs.impl.LayerImpl#getMetamodels <em>Metamodels</em>}</li>
 *   <li>{@link mrs.impl.LayerImpl#getModularReferenceStructure <em>Modular Reference Structure</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LayerImpl extends MinimalEObjectImpl.Container implements Layer {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

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
	 * The cached value of the '{@link #getMetamodels() <em>Metamodels</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetamodels()
	 * @generated
	 * @ordered
	 */
	protected EList<Metamodel> metamodels;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LayerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MrsPackage.Literals.LAYER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MrsPackage.LAYER__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Metamodel> getMetamodels() {
		if (metamodels == null) {
			metamodels = new EObjectContainmentWithInverseEList<Metamodel>(Metamodel.class, this, MrsPackage.LAYER__METAMODELS, MrsPackage.METAMODEL__LAYER);
		}
		return metamodels;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ModularReferenceStructure getModularReferenceStructure() {
		if (eContainerFeatureID() != MrsPackage.LAYER__MODULAR_REFERENCE_STRUCTURE) return null;
		return (ModularReferenceStructure)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModularReferenceStructure(ModularReferenceStructure newModularReferenceStructure, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newModularReferenceStructure, MrsPackage.LAYER__MODULAR_REFERENCE_STRUCTURE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setModularReferenceStructure(ModularReferenceStructure newModularReferenceStructure) {
		if (newModularReferenceStructure != eInternalContainer() || (eContainerFeatureID() != MrsPackage.LAYER__MODULAR_REFERENCE_STRUCTURE && newModularReferenceStructure != null)) {
			if (EcoreUtil.isAncestor(this, newModularReferenceStructure))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newModularReferenceStructure != null)
				msgs = ((InternalEObject)newModularReferenceStructure).eInverseAdd(this, MrsPackage.MODULAR_REFERENCE_STRUCTURE__LAYERS, ModularReferenceStructure.class, msgs);
			msgs = basicSetModularReferenceStructure(newModularReferenceStructure, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MrsPackage.LAYER__MODULAR_REFERENCE_STRUCTURE, newModularReferenceStructure, newModularReferenceStructure));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MrsPackage.LAYER__METAMODELS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getMetamodels()).basicAdd(otherEnd, msgs);
			case MrsPackage.LAYER__MODULAR_REFERENCE_STRUCTURE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetModularReferenceStructure((ModularReferenceStructure)otherEnd, msgs);
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
			case MrsPackage.LAYER__METAMODELS:
				return ((InternalEList<?>)getMetamodels()).basicRemove(otherEnd, msgs);
			case MrsPackage.LAYER__MODULAR_REFERENCE_STRUCTURE:
				return basicSetModularReferenceStructure(null, msgs);
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
			case MrsPackage.LAYER__MODULAR_REFERENCE_STRUCTURE:
				return eInternalContainer().eInverseRemove(this, MrsPackage.MODULAR_REFERENCE_STRUCTURE__LAYERS, ModularReferenceStructure.class, msgs);
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
			case MrsPackage.LAYER__NAME:
				return getName();
			case MrsPackage.LAYER__METAMODELS:
				return getMetamodels();
			case MrsPackage.LAYER__MODULAR_REFERENCE_STRUCTURE:
				return getModularReferenceStructure();
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
			case MrsPackage.LAYER__NAME:
				setName((String)newValue);
				return;
			case MrsPackage.LAYER__METAMODELS:
				getMetamodels().clear();
				getMetamodels().addAll((Collection<? extends Metamodel>)newValue);
				return;
			case MrsPackage.LAYER__MODULAR_REFERENCE_STRUCTURE:
				setModularReferenceStructure((ModularReferenceStructure)newValue);
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
			case MrsPackage.LAYER__NAME:
				setName(NAME_EDEFAULT);
				return;
			case MrsPackage.LAYER__METAMODELS:
				getMetamodels().clear();
				return;
			case MrsPackage.LAYER__MODULAR_REFERENCE_STRUCTURE:
				setModularReferenceStructure((ModularReferenceStructure)null);
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
			case MrsPackage.LAYER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case MrsPackage.LAYER__METAMODELS:
				return metamodels != null && !metamodels.isEmpty();
			case MrsPackage.LAYER__MODULAR_REFERENCE_STRUCTURE:
				return getModularReferenceStructure() != null;
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

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //LayerImpl
