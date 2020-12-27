INSERT INTO fansub (id, slug, name) VALUES
(default, 'fate4anime', 'Fate4Anime');

INSERT INTO public.fansub_link (id, fansub_id, link, link_type) VALUES
(default, 1, 'https://fate4anime.com/', 'WEBSITE'),
(default, 1, 'https://discord.com/invite/v54872Jc', 'DISCORD');

INSERT INTO public.fansub_member (id, fansub_id, name, role, contact, contact_type) VALUES
(default, 1, 'Hitman', 'Fundador', 'Hitman#8124', 'DISCORD'),
(default, 1, 'JoosJoestar', 'Tradutor', 'JoosJoestar#0001', 'DISCORD');