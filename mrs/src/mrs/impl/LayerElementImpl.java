/**
 */
package mrs.impl;

import mrs.Layer;
import mrs.LayerElement;
import mrs.MrsPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Layer Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link mrs.impl.LayerElementImpl#getLayer <em>Layer</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class LayerElementImpl extends MinimalEObjectImpl.Container implements LayerElement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LayerElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MrsPackage.Literals.LAYER_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Layer getLayer() {
		if (eContainerFeatureID() != MrsPackage.LAYER_ELEMENT__LAYER) return null;
		return (Layer)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLayer(Layer newLayer, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newLayer, MrsPackage.LAYER_ELEMENT__LAYER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLayer(Layer newLayer) {
		if (newLayer != eInternalContainer() || (eContainerFeatureID() != MrsPackage.LAYER_ELEMENT__LAYER && newLayer != null)) {
			if (EcoreUtil.isAncestor(this, newLayer))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newLayer != null)
				msgs = ((InternalEObject)newLayer).eInverseAdd(this, MrsPackage.LAYER__LAYER_ELEMENTS, Layer.class, msgs);
			msgs = basicSetLayer(newLayer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MrsPackage.LAYER_ELEMENT__LAYER, newLayer, newLayer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MrsPackage.LAYER_ELEMENT__LAYER:
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
			case MrsPackage.LAYER_ELEMENT__LAYER:
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
			case MrsPackage.LAYER_ELEMENT__LAYER:
				return eInternalContainer().eInverseRemove(this, MrsPackage.LAYER__LAYER_ELEMENTS, Layer.class, msgs);
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
			case MrsPackage.LAYER_ELEMENT__LAYER:
				return getLayer();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MrsPackage.LAYER_ELEMENT__LAYER:
				setLayer((Layer)newValue);
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
			case MrsPackage.LAYER_ELEMENT__LAYER:
				setLayer((Layer)null);
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
			case MrsPackage.LAYER_ELEMENT__LAYER:
				return getLayer() != null;
		}
		return super.eIsSet(featureID);
	}

} //LayerElementImpl
