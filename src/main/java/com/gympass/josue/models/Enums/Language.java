package com.gympass.josue.models.Enums;

public enum Language {
    PT {
        public String toString() {
            return "portuguese";
        }
    },
    EN{
        public String toString() {
            return "english";
        }
    },
    KR{
        public String toString() {
            return "korean";
        }
    },
    JP{
        public String toString() {
            return "japanese";
        }
    },
    ES{
        public String toString() {
            return "spanish";
        }
    },
    GE{
        public String toString() {
            return "german";
        }
    },
    FR{
        public String toString() {
            return "french";
        }
    },
    UNKNOWN{
        public String toString() {
            return "language not provided";
        }
    }
}
