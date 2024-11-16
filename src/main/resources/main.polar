# Define global roles
actor User {}

resource Application {
    roles = ["user"];
    permissions = [
        "register-conference"
    ];

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

}

# Application rules
has_role(_: Actor, "user", _: Application) if
    true;

allow(actor: Actor, "register-conference", app: Application) if
    has_role(actor, "user", app);


# Conference rules
has_role(actor: Actor, "user", conf: Conference) if
    app matches Application and
    has_role(actor, "user", app) and
    has_relation(conf, "application", app);

has_role(actor: Actor, "user", conf: Conference) if
    has_role(actor, "organizer", conf);

# user permissions for Conference
allow(actor: Actor, "view", conf: Conference) if
    has_role(actor, "user", conf);

allow(actor: Actor, "request-talk-registration", conf: Conference) if
    has_role(actor, "user", conf);

# organizer permissions for Conference
allow(actor: Actor, "edit", conf: Conference) if
    has_role(actor, "organizer", conf);

allow(actor: Actor, "cancel", conf: Conference) if
    has_role(actor, "organizer", conf);


#Talk rules
has_role(actor: Actor, "user", talk: Talk) if
    conf matches Conference and
    has_role(actor, "user", conf) and
    has_relation(talk, "conference", conf);

has_role(actor: Actor, "conf-organizer", talk: Talk) if
    conf matches Conference and
    has_role(actor, "organizer", conf) and
    has_relation(talk, "conference", conf);

has_role(actor: Actor, "user", conf: Conference) if
    has_role(actor, "conf-organizer", conf);

# user permissions for Talk
allow(actor: Actor, "view", talk: Talk) if
    has_role(actor, "user", talk);

allow(actor: Actor, "sing-up", talk: Talk) if
    has_role(actor, "user", talk);

# speaker permissions for Talk
allow(actor: Actor, "edit", talk: Talk) if
    has_role(actor, "speaker", talk);

allow(actor: Actor, "cancel", talk: Talk) if
    has_role(actor, "speaker", talk);

# listener permissions for Talk
allow(actor: Actor, "drop-out", talk: Talk) if
    has_role(actor, "listener", talk);

# conf-organizer permissions for Talk
allow(actor: Actor, "cancel", talk: Talk) if
    has_role(actor, "conf-organizer", talk);