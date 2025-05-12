package sen.saloum.Ramli.enums;

public enum Role {
    ADMIN,
    UTILISATEUR,
    INVITE;

    public static Role fromName(String name) {
        for (Role role : Role.values()) {
            if (role.name().equalsIgnoreCase(name)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Aucun rôle correspondant à: " + name);
    }
}
