# Define global roles
actor User {}

resource Application {
    roles = ["user"];
    permissions = [
        "register-conference"
    ];

    "register-conference" if "user";
}

# Define resources with roles
resource Conference {
    roles = ["user", "organizer"];
    permissions = [
        "view",
        "request-talk-registration",
        "edit",
        "cancel"
    ];
    relations = {
        application: Application,
    };

    # user permissions
    "user" if "user" on "application";
    "view" if "user";
    "request-talk-registration" if "user";

    # organizer permissions
    "view" if "organizer";
    "edit" if "organizer";
    "cancel" if "organizer";
}

resource Talk {
    roles = [
        "user",
        "speaker",
        "listener",
        "conf-organizer"
    ];
    permissions = [
            "view",
            "sign-up",
            "drop-out",
            "edit",
            "cancel"
    ];
    relations = {
        conference: Conference,
    };

    # user permissions;
    "user" if "user" on "conference";
    "view" if "user";
    "sign-up" if "user";

    # speaker permissions
    "edit" if "speaker";
    "cancel" if "speaker";

    # listener permissions
    "drop-out" if "listener";

    # organizer permissions
    "conf-organizer" if "organizer" on "conference";
    "view" if "conf-organizer";
    "cancel" if "conf-organizer";

}

has_role(_: Actor, "user", _: Application) if
    true;

allow(actor: Actor, "register-conference", app: Application) if
    has_role(actor, "user", app);

