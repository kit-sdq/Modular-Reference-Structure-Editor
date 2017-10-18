package mrs.design;

import org.eclipse.emf.ecore.EClassifier;

public class Dependency {
    private EClassifier source;
    private EClassifier target;
    private DependencyType type;
    
    
    public Dependency(EClassifier source, EClassifier target, DependencyType type) {
        this.source = source;
        this.target = target;
        this.type = type;
    }


    public EClassifier getSource() {
        return source;
    }

    public EClassifier getTarget() {
        return target;
    }

    public DependencyType getType() {
        return type;
    }

    public enum DependencyType {
        E_SUPER_TYPE ("Supertype"), 
        E_REFERENCE ("EReference"), 
        E_ATTRIBUTE("EAttribute"),
        E_OPERATION_RETURN_TYPE ("Return type of EOperation"), 
        E_OPERATION_PARAMETER ("Parameter type of EOperation"), 
        E_GENERIC_TYPE ("EGenericType");
        
        private final String stringRepresentation;
        
        private DependencyType(String stringReprensentation) {
            this.stringRepresentation = stringReprensentation;
        }
        
        @Override
        public String toString() {
            return stringRepresentation;
        }
    }
}
