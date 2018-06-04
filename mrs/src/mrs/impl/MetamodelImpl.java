/**
 */
package mrs.impl;

import mrs.Metamodel;
import mrs.MrsPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Metamodel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link mrs.impl.MetamodelImpl#getMainPackage <em>Main Package</em>}</li>
 *   <li>{@link mrs.impl.MetamodelImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MetamodelImpl extends LayerElementImpl implements Metamodel {
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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MrsPackage.METAMODEL__MAIN_PACKAGE:
				if (resolve) return getMainPackage();
				return basicGetMainPackage();
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
