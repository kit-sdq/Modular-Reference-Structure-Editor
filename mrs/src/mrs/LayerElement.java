/**
 */
package mrs;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Layer Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link mrs.LayerElement#getLayer <em>Layer</em>}</li>
 * </ul>
 *
 * @see mrs.MrsPackage#getLayerElement()
 * @model abstract="true"
 * @generated
 */
public interface LayerElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Layer</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link mrs.Layer#getLayerElements <em>Layer Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Layer</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Layer</em>' container reference.
	 * @see #setLayer(Layer)
	 * @see mrs.MrsPackage#getLayerElement_Layer()
	 * @see mrs.Layer#getLayerElements
	 * @model opposite="layerElements" required="true" transient="false"
	 * @generated
	 */
	Layer getLayer();

	/**
	 * Sets the value of the '{@link mrs.LayerElement#getLayer <em>Layer</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Layer</em>' container reference.
	 * @see #getLayer()
	 * @generated
	 */
	void setLayer(Layer value);

} // LayerElement
