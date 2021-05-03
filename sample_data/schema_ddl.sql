DROP TABLE IF EXISTS public.game_state;
CREATE TABLE public.game_state (
                                   id serial NOT NULL PRIMARY KEY,
                                   current_map text NOT NULL,
                                   saved_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                   hero_name text NOT NULL
);

DROP TABLE IF EXISTS public.player;
CREATE TABLE public.player (
                               id serial NOT NULL PRIMARY KEY,
                               player_name text NOT NULL,
                               hp integer NOT NULL,
                               max_hp integer NOT NULL,
                               attack integer NOT NULL,
                               x integer NOT NULL,
                               y integer NOT NULL,
                               score integer,
                               UNIQUE (player_name)
);

DROP TABLE IF EXISTS public.inventory;
CREATE TABLE public.inventory (
                                  hero_name text NOT NULL,
                                  blue_key integer NOT NULL DEFAULT 0,
                                  yellow_key integer NOT NULL DEFAULT 0,
                                  potion integer NOT NULL DEFAULT 0
);



ALTER TABLE ONLY public.game_state
    ADD CONSTRAINT fk_hero_name FOREIGN KEY (hero_name) REFERENCES public.player(player_name);


ALTER TABLE ONLY public.inventory
    ADD CONSTRAINT fk_hero_name FOREIGN KEY (hero_name) REFERENCES public.player(player_name);