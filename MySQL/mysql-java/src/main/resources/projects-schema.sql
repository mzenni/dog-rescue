DROP TABLE IF EXISTS project_category;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS step;
DROP TABLE IF EXISTS material;
DROP TABLE IF EXISTS project; 

CREATE TABLE project(
	project_id int NOT NULL AUTO_INCREMENT,
	project_name varchar(128) NOT NULL,
	estimated_hours decimal(7,2),
	actual_hours decimal(7,2), 
	difficulty int, 
	notes text, 
	PRIMARY KEY (project_id)
);

CREATE TABLE material(
	material_id int NOT NULL AUTO_INCREMENT, 
	project_id int NOT NULL,
	material_name varchar(128) NOT NULL,
	num_required int,
	cost decimal(7,2),
	PRIMARY KEY (material_id),
	FOREIGN KEY (project_id) REFERENCES project(project_id) ON DELETE CASCADE
);

CREATE TABLE step(
	step_id int NOT NULL AUTO_INCREMENT, 
	project_id int NOT NULL,
	step_text text NOT NULL,
	step_order int NOT NULL,
	PRIMARY KEY (step_id),
	FOREIGN KEY (project_id) REFERENCES project(project_id) ON DELETE CASCADE
);

CREATE TABLE category(
	category_id INT AUTO_INCREMENT NOT NULL,
	category_name varchar(128) NOT NULL,
	PRIMARY KEY (category_id) 
);

CREATE TABLE project_category(
	project_id int NOT NULL,
	category_id int NOT NULL,
	FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE,
	FOREIGN KEY (category_id) REFERENCES category (category_id) ON DELETE CASCADE,
	UNIQUE KEY (project_id, category_id)
);

-- Add some data


INSERT INTO project (project_id, project_name, estimated_hours, actual_hours, difficulty, notes) VALUES 
(1,'Hang a door', 10, 15, 3, 'Door hangers from home depot'), (2,'Repairs', 2, 1, 2, 'Patch wall'), (3,'Gardening', 2, 1, 1, 'Gardening');

INSERT INTO category (category_id, category_name) VALUES (1,'Doors and Windows'), (2,'Repairs'), (3,'Gardening');

INSERT INTO material (project_id, material_name, num_required) VALUES (1, '2-inch screws', 20), (2, 'Drain Cleaner',1), (3, 'Gloves', 1);

INSERT INTO step (project_id, step_text, step_order) VALUES 
(1, 'Screw door hangers on the top, middle, and bottom of one side of the closet door frame', 1),
(1, 'Screw door hangers on top, middle, and bottom of door', 2),
(1, 'Use pin to join hangers on door and hangers on door frame', 3),
(2, 'Spackle wall',1),
(2, 'Wait for spackle to dry',2),
(2, 'Use sandpaper to smooth out patch',3),
(2, 'Paint patch and let dry',4),
(3, 'Rake leaves into pile', 1),
(3, 'Collect leaves', 2),
(3, 'Put leaves into compost container', 3);

INSERT INTO project_category (project_id, category_id) VALUES (1, 1), (2, 2), (3, 3);
