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

INSERT INTO project (project_name, estimated_hours, actual_hours, difficulty, notes) VALUES ('Hang closet door', 4.50, 3.50, 4, 'Use door hangers from Home Depot'); 
INSERT INTO material (project_id, material_name, num_required, cost) VALUES (1, 'Door hangers', 3); 
INSERT INTO material (project_id, material_name, num_required, cost) VALUES (2, 'Package of door hangers from Home Depot', 1);
INSERT INTO step (project_id, step_text, step_order) VALUES (1, 'Align hangers on opening side of door vertically on the wall', 1); 
INSERT INTO step (project_id, step_text, step_order) VALUES (1, 'Screw hangers into frame', 2); 
INSERT INTO category (category_id, category_name) VALUES (1, 'Doors and Windows'); 
INSERT INTO category (category_id, category_name) VALUES (2, 'Repairs');
INSERT INTO category (category_id, category_name) VALUES (3, 'Gardening');
INSERT INTO project_category (project_id, category_id) VALUES (1, 1);
INSERT INTO project_category (project_id, category_id) VALUES (1, 2); 