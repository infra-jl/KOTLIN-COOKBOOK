CREATE TABLE ingredient
(
    id               SERIAL                   NOT NULL,
    version          INTEGER                  NOT NULL DEFAULT 0,
    created_at       TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    last_modified_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    recipe_id        INT                      NOT NULL,
    name             TEXT                     NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (recipe_id) REFERENCES recipe
);
