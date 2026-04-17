
-- ==========================================
-- JEU D'ESSAI INITIAL - BASE RESTO
-- ==========================================

USE
    `resto`;

-- 1. Insertion des statuts de commande
-- Toujours insérer les nomenclatures en premier pour éviter les erreurs de clés étrangères
INSERT INTO `status` (`label_status`)
VALUES ('En attente'),
       ('En préparation'),
       ('Prête'),
       ('Livrée'),
       ('Annulée');

-- 2. Insertion des clients (Table client)
-- Attention à la colonne `last_name_client` définie dans le schéma
INSERT INTO `clients` (`first_name_client`, `last_name_client`)
VALUES ('Jean', 'Dupont'),
       ('Marie', 'Curie'),
       ('Alan', 'Turing'),
       ('Ada', 'Lovelace');

-- 3. Insertion des plats/pizzas (Table dish)
INSERT INTO `dishes` (`name_dish`, `price_dish`, `description_dish`, `available_dish`, `category_dish`)
VALUES ('Pizza Margherita', 10.00, 'Sauce tomate, mozzarella fraîche, basilic', 1, 'pizza'),
       ('Pizza 4 Fromages', 13.50, 'Sauce tomate, mozzarella, chèvre, gorgonzola, emmental', 1, 'pizza'),
       ('Pizza Reine', 11.50, 'Sauce tomate, mozzarella, jambon blanc, champignons frais', 1, 'pizza'),
       ('Tiramisu Maison', 5.50, 'Dessert traditionnel italien au café et mascarpone', 1, 'dessert'),
       ('Coca-Cola', 2.50, 'Canette 33cl', 1, 'boisson');

-- 4. Insertion des commandes (Table order_)
-- On simule quelques commandes passées aujourd'hui.
-- Les id_status font référence aux ID de 1 à 5 créés ci-dessus.
-- Les id_client font référence aux ID de 1 à 4 créés ci-dessus.
INSERT INTO `orders` (`daily_id_order`, `date_creation_order`, `id_status`, `id_client`)
VALUES (1, '2026-04-01 12:15:00', 4, 1), -- Commande 1 : Livrée pour Jean
       (2, '2026-04-01 12:45:00', 3, 2), -- Commande 2 : Prête pour Marie
       (3, '2026-04-01 19:30:00', 2, 3), -- Commande 3 : En préparation pour Alan
       (4, '2026-04-01 19:45:00', 1, 4);
-- Commande 4 : En attente pour Ada

-- 5. Insertion des détails de commande (Table order_item)
-- Association entre les commandes (id_order) et les plats (id_dish) avec la quantité
INSERT INTO `order_items` (`id_order`, `id_dish`, `quantity_order_item`)
VALUES
-- Commande 1 (Jean) : 2 Margherita, 2 Coca
(1, 1, 2),
(1, 5, 2),
-- Commande 2 (Marie) : 1 Reine, 1 Tiramisu
(2, 3, 1),
(2, 4, 1),
-- Commande 3 (Alan) : 1 4 Fromages, 1 Margherita, 2 Coca
(3, 2, 1),
(3, 1, 1),
(3, 5, 2),
-- Commande 4 (Ada) : 2 Reine
(4, 3, 2);