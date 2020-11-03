/**
 */
package mrs.impl;

import java.util.Collection;

import mrs.Layer;
import mrs.ModularReferenceStructure;
import mrs.MrsPackage;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.modelversioning.emfprofile.Profile;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Modular Reference Structure</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link mrs.impl.ModularReferenceStructureImpl#getLayers <em>Layers</em>}</li>
 *   <li>{@link mrs.impl.ModularReferenceStructureImpl#getLoadedProfiles <em>Loaded Profiles</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ModularReferenceStructureImpl extends MinimalEObjectImpl.Container implements ModularReferenceStructure {
	/**
	 * The cached value of the '{@link #getLayers() <em>Layers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLayers()
	 * @generated
	 * @ordered
	 */
	protected EList<Layer> layers;

	/**
	 * The cached value of the '{@link #getLoadedProfiles() <em>Loaded Profiles</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getLoadedProfiles()
	 * @generated
	 * @ordered
	 */
    protected EList<Profile> loadedProfiles;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModularReferenceStructureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MrsPackage.Literals.MODULAR_REFERENCE_STRUCTURE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Layer> getLayers() {
		if (layers == null) {
			layers = new EObjectContainmentWithInverseEList<Layer>(Layer.class, this, MrsPackage.MODULAR_REFERENCE_STRUCTURE__LAYERS, MrsPackage.LAYER__MODULAR_REFERENCE_STRUCTURE);
		}
		return layers;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public EList<Profile> getLoadedProfiles() {
		if (loadedProfiles == null) {
			loadedProfiles = new EObjectResolvingEList<Profile>(Profile.class, this, MrsPackage.MODULAR_REFERENCE_STRUCTURE__LOADED_PROFILES);
		}
		return loadedProfiles;
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
			case MrsPackage.MODULAR_REFERENCE_STRUCTURE__LAYERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getLayers()).basicAdd(otherEnd, msgs);
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
			case MrsPackage.MODULAR_REFERENCE_STRUCTURE__LAYERS:
				return ((InternalEList<?>)getLayers()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MrsPackage.MODULAR_REFERENCE_STRUCTURE__LAYERS:
				return getLayers();
			case MrsPackage.MODULAR_REFERENCE_STRUCTURE__LOADED_PROFILES:
				return getLoadedProfiles();
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
			case MrsPackage.MODULAR_REFERENCE_STRUCTURE__LAYERS:
				getLayers().clear();
				getLayers().addAll((Collection<? extends Layer>)newValue);
				return;
			case MrsPackage.MODULAR_REFERENCE_STRUCTURE__LOADED_PROFILES:
				getLoadedProfiles().clear();
				getLoadedProfiles().addAll((Collection<? extends Profile>)newValue);
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
			case MrsPackage.MODULAR_REFERENCE_STRUCTURE__LAYERS:
				getLayers().clear();
				return;
			case MrsPackage.MODULAR_REFERENCE_STRUCTURE__LOADED_PROFILES:
				getLoadedProfiles().clear();
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
			case MrsPackage.MODULAR_REFERENCE_STRUCTURE__LAYERS:
				return layers != null && !layers.isEmpty();
			case MrsPackage.MODULAR_REFERENCE_STRUCTURE__LOADED_PROFILES:
				return loadedProfiles != null && !loadedProfiles.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ModularReferenceStructureImpl
