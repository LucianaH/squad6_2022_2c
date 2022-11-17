package com.aninfo.enums;

public enum Estado {
    BORRADOR {
        public String toString() {
            return "borrador";
        }
    },

    ENVIADO {
        public String toString() {
            return "emitido";
        }
    },

    APROBADO {
        public String toString() {
            return "aprobado";
        }
    },

    RECHAZADO {
        public String toString() {
            return "rechazado";
        }
    }
}
