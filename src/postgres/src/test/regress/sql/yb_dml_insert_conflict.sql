--
-- insert...on conflict on constraint
--
create table tab(i int primary key, j int);
create unique index on tab(j);
create unique index tab_idx on tab(j);
-- Insert some data
insert into tab values(1, 1);
insert into tab values(200, 200);
-- Use generated constraint name for primary key.
insert into tab values(1, 7) ON CONFLICT ON CONSTRAINT tab_pkey DO UPDATE
		SET j = tab.j + excluded.j;
-- Use SELECT to verify result.
SELECT * FROM tab;
-- User generated name for unique index.
insert into tab values(1, 200) ON CONFLICT ON CONSTRAINT tab_j_idx DO UPDATE
		SET j = tab.j + excluded.j;
-- Error: Name of index is not a constraint name.
insert into tab values(1, 1) ON CONFLICT ON CONSTRAINT tab_idx DO NOTHING;
-- Use conflict for unique column
insert into tab values(1, 200) ON CONFLICT (j) DO UPDATE
		SET j = tab.j + excluded.j;
-- Use SELECT to verify result.
SELECT * FROM tab;
