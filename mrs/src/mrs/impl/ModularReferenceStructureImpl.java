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
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Modular Reference Structure</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link mrs.impl.ModularReferenceStructureImpl#getLayers <em>Layers</em>}</li>
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
        }
        return super.eIsSet(featureID);
    }

} //ModularReferenceStructureImpl
