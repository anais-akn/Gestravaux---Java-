-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Hôte : database:3306
-- Généré le : ven. 27 fév. 2026 à 15:35
-- Version du serveur : 10.11.2-MariaDB-1:10.11.2+maria~ubu2204
-- Version de PHP : 8.3.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `app_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `bien`
--

CREATE TABLE `bien` (
  `id` int(11) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `ville` varchar(100) NOT NULL,
  `code_postal` varchar(20) NOT NULL,
  `surface` decimal(10,2) NOT NULL,
  `utilisateur_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `bien`
--

INSERT INTO `bien` (`id`, `adresse`, `ville`, `code_postal`, `surface`, `utilisateur_id`) VALUES
(1, '12 Rue des Lilas', 'Paris', '75020', 45.50, 1),
(2, '45 Boulevard Haussmann', 'Paris', '75009', 120.00, 1),
(3, '8 Rue du Rhône', 'Lyon', '69001', 65.00, 2),
(4, '10 Rue de la Grange aux Belles', 'Paris', '75010', 45.00, 1),
(5, '45 Avenue Victor Hugo', 'Boulogne-Billancourt', '92100', 120.00, 1),
(6, '12 Rue de la République', 'Saint-Denis', '93200', 95.00, 1),
(7, '2 Rue de la Paroisse', 'Versailles', '78000', 65.00, 1),
(8, '8 Rue des Écoles', 'Créteil', '94000', 110.00, 1),
(9, '15 Rue Victor Hugo', 'Lyon', '69002', 72.00, 1),
(10, '20 Cours Émile Zola', 'Villeurbanne', '69100', 68.00, 1),
(11, '5 Rue Joliot Curie', 'Vénissieux', '69200', 350.00, 1),
(12, '1 Rue des Monts d Or', 'Limonest', '69760', 180.00, 1),
(13, '12 Avenue Franklin Roosevelt', 'Bron', '69500', 105.00, 1),
(14, '50 Rue Sainte-Catherine', 'Bordeaux', '33000', 55.00, 1),
(15, '100 Avenue de l Yser', 'Mérignac', '33700', 210.00, 1),
(16, '15 Rue des Vignes', 'Pessac', '33600', 140.00, 1),
(17, '5 Cours de la Libération', 'Talence', '33400', 42.00, 1),
(18, '2 Avenue René Cassin', 'Cenon', '33150', 88.00, 1);

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `id` int(11) NOT NULL,
  `libelle` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`id`, `libelle`) VALUES
(1, 'Plomberie'),
(2, 'Électricité'),
(3, 'Peinture'),
(4, 'Maçonnerie'),
(5, 'Menuiserie');

-- --------------------------------------------------------

--
-- Structure de la table `chantier`
--

CREATE TABLE `chantier` (
  `id` int(11) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_validation` datetime DEFAULT NULL,
  `statut` varchar(50) NOT NULL,
  `description` longtext NOT NULL,
  `bien_id` int(11) NOT NULL,
  `inspecteur_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `chantier`
--

INSERT INTO `chantier` (`id`, `date_creation`, `date_validation`, `statut`, `description`, `bien_id`, `inspecteur_id`) VALUES
(1, '2026-02-01 10:00:00', '2026-02-05 14:00:00', 'Validé', 'Rénovation cuisine et SDB', 1, 1),
(2, '2026-02-15 09:30:00', NULL, 'En attente', 'Rafraîchissement séjour peinture', 2, 1),
(3, '2026-02-20 11:00:00', '2026-02-22 10:00:00', 'En cours', 'Électricité complète', 3, 2),
(4, '2026-02-26 18:12:21', NULL, 'OUVERT', 'Chantier créé via l\'app', 2, 2),
(5, '2026-02-26 18:25:09', NULL, 'OUVERT', 'Chantier créé via l\'app', 2, 2),
(6, '2026-02-26 18:32:43', NULL, 'OUVERT', 'Chantier créé via l\'app', 2, 2),
(7, '2026-02-26 18:45:03', NULL, 'OUVERT', 'Chantier créé via l\'app', 2, 2),
(8, '2026-02-26 20:52:40', NULL, 'OUVERT', 'Chantier créé via l\'app', 1, 1),
(9, '2026-02-26 21:10:40', NULL, 'OUVERT', 'Chantier créé via l\'app', 3, 2),
(10, '2026-02-26 21:13:50', NULL, 'OUVERT', 'Chantier créé via l\'app', 3, 2),
(11, '2026-02-26 21:34:54', NULL, 'OUVERT', 'Chantier créé via l\'app', 2, 1),
(12, '2026-02-27 11:42:00', NULL, 'OUVERT', 'Chantier créé via l\'app', 2, 1),
(13, '2026-02-27 11:46:01', NULL, 'OUVERT', 'Chantier créé via l\'app', 1, 2),
(14, '2026-02-27 11:47:46', NULL, 'OUVERT', 'Chantier créé via l\'app', 2, 1),
(15, '2026-02-27 11:51:18', NULL, 'OUVERT', 'Chantier créé via l\'app', 3, 2),
(16, '2026-02-27 11:51:44', NULL, 'OUVERT', 'Chantier créé via l\'app', 1, 1),
(17, '2026-02-27 12:00:57', NULL, 'OUVERT', 'Chantier créé via l\'app', 1, 1),
(18, '2026-02-27 14:02:47', NULL, 'OUVERT', 'Chantier créé via l\'app', 2, 2),
(19, '2026-02-27 14:07:45', NULL, 'OUVERT', 'Chantier créé via l\'app', 2, 2),
(20, '2026-02-27 14:08:16', NULL, 'OUVERT', 'Chantier créé via l\'app', 3, 1),
(21, '2026-02-27 15:34:06', NULL, 'OUVERT', 'Chantier créé via l\'app', 10, 1);

-- --------------------------------------------------------

--
-- Structure de la table `devis_entrepreneur`
--

CREATE TABLE `devis_entrepreneur` (
  `id` int(11) NOT NULL,
  `date_debut` datetime NOT NULL,
  `duree_estimee_jour` int(11) NOT NULL,
  `statut` varchar(50) NOT NULL,
  `entrepreneur_id` int(11) DEFAULT NULL,
  `chantier_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `devis_entrepreneur`
--

INSERT INTO `devis_entrepreneur` (`id`, `date_debut`, `duree_estimee_jour`, `statut`, `entrepreneur_id`, `chantier_id`) VALUES
(1, '2026-03-01 08:00:00', 3, 'Accepté', 1, 1),
(2, '2026-03-10 08:00:00', 5, 'En attente', 3, 2);

-- --------------------------------------------------------

--
-- Structure de la table `devis_entrepreneur_prestataire`
--

CREATE TABLE `devis_entrepreneur_prestataire` (
  `id` int(11) NOT NULL,
  `prix_unitaire` decimal(10,2) NOT NULL,
  `devis_entrepeneur_id` int(11) DEFAULT NULL,
  `prestation_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `devis_entrepreneur_prestataire`
--

INSERT INTO `devis_entrepreneur_prestataire` (`id`, `prix_unitaire`, `devis_entrepeneur_id`, `prestation_id`) VALUES
(1, 145.00, 1, 1),
(2, 430.00, 1, 2),
(3, 22.50, 2, 4);

-- --------------------------------------------------------

--
-- Structure de la table `devis_type`
--

CREATE TABLE `devis_type` (
  `id` int(11) NOT NULL,
  `intitule` varchar(200) NOT NULL,
  `date_creation` datetime NOT NULL,
  `chantier_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `devis_type`
--

INSERT INTO `devis_type` (`id`, `intitule`, `date_creation`, `chantier_id`) VALUES
(1, 'Estimation Plomberie Cuisine', '2026-02-05 00:00:00', 1),
(2, 'Estimation Peinture Séjour', '2026-02-16 00:00:00', 2),
(3, 'Devis Type - 45 Boulevard Haussmann', '2026-02-26 18:12:21', 4),
(4, 'Devis Type - 45 Boulevard Haussmann', '2026-02-26 18:25:09', 5),
(5, 'Devis Type - 45 Boulevard Haussmann', '2026-02-26 18:32:43', 6),
(6, 'Devis Type - 45 Boulevard Haussmann', '2026-02-26 18:45:03', 7),
(7, 'Devis Type - 12 Rue des Lilas', '2026-02-26 20:52:40', 8),
(8, 'Devis Type - 8 Rue du Rhône', '2026-02-26 21:10:40', 9),
(9, 'Devis Type - 8 Rue du Rhône', '2026-02-26 21:13:50', 10),
(10, 'Devis Type - 45 Boulevard Haussmann', '2026-02-26 21:34:54', 11),
(11, 'Devis Type - 45 Boulevard Haussmann', '2026-02-27 11:42:00', 12),
(12, 'Devis Type - 12 Rue des Lilas', '2026-02-27 11:46:01', 13),
(13, 'Devis Type - 45 Boulevard Haussmann', '2026-02-27 11:47:46', 14),
(14, 'Devis Type - 8 Rue du Rhône', '2026-02-27 11:51:18', 15),
(15, 'Devis Type - 12 Rue des Lilas', '2026-02-27 11:51:45', 16),
(16, 'Devis Type - 12 Rue des Lilas', '2026-02-27 12:00:57', 17),
(17, 'Devis Type - 45 Boulevard Haussmann', '2026-02-27 14:02:47', 18),
(18, 'Devis Type - 45 Boulevard Haussmann', '2026-02-27 14:07:45', 19),
(19, 'Devis Type - 8 Rue du Rhône', '2026-02-27 14:08:16', 20),
(20, 'Devis Type - 20 Cours Émile Zola', '2026-02-27 15:34:06', 21);

-- --------------------------------------------------------

--
-- Structure de la table `devis_type_entrepreneur`
--

CREATE TABLE `devis_type_entrepreneur` (
  `devis_type_id` int(11) NOT NULL,
  `entrepreneur_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `devis_type_entrepreneur`
--

INSERT INTO `devis_type_entrepreneur` (`devis_type_id`, `entrepreneur_id`) VALUES
(9, 1),
(9, 2),
(10, 2),
(17, 3),
(18, 3),
(19, 2),
(20, 2),
(20, 9);

-- --------------------------------------------------------

--
-- Structure de la table `devis_type_prestataire`
--

CREATE TABLE `devis_type_prestataire` (
  `devis_type_id` int(11) NOT NULL,
  `prestataire_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `devis_type_prestation`
--

CREATE TABLE `devis_type_prestation` (
  `id` int(11) NOT NULL,
  `quantite` int(11) NOT NULL,
  `devis_type_id` int(11) NOT NULL,
  `prestataire_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `devis_type_prestation`
--

INSERT INTO `devis_type_prestation` (`id`, `quantite`, `devis_type_id`, `prestataire_id`) VALUES
(1, 1, 1, 1),
(2, 1, 1, 2),
(3, 40, 2, 4),
(4, 1, 3, 5),
(5, 1, 3, 1),
(6, 2, 3, 2),
(7, 2, 4, 2),
(8, 1, 4, 4),
(9, 3, 5, 1),
(10, 2, 5, 4),
(11, 2, 6, 2),
(12, 1, 6, 4),
(13, 2, 7, 1),
(14, 2, 8, 4),
(15, 2, 9, 1),
(16, 2, 10, 3),
(17, 1, 11, 4),
(18, 1, 12, 5),
(19, 1, 13, 1),
(20, 1, 14, 4),
(21, 1, 15, 4),
(22, 1, 16, 4),
(23, 1, 17, 4),
(24, 1, 18, 4),
(25, 1, 19, 4),
(26, 1, 20, 4),
(27, 1, 20, 1);

-- --------------------------------------------------------

--
-- Structure de la table `doctrine_migration_versions`
--

CREATE TABLE `doctrine_migration_versions` (
  `version` varchar(191) NOT NULL,
  `executed_at` datetime DEFAULT NULL,
  `execution_time` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `doctrine_migration_versions`
--

INSERT INTO `doctrine_migration_versions` (`version`, `executed_at`, `execution_time`) VALUES
('DoctrineMigrations\\Version20260122103346', '2026-01-22 20:32:56', 2760);

-- --------------------------------------------------------

--
-- Structure de la table `document`
--

CREATE TABLE `document` (
  `id` int(11) NOT NULL,
  `type_document` varchar(100) NOT NULL,
  `chemin_fichier` varchar(500) NOT NULL,
  `date_upload` datetime NOT NULL,
  `chantier_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `document`
--

INSERT INTO `document` (`id`, `type_document`, `chemin_fichier`, `date_upload`, `chantier_id`) VALUES
(1, 'Plan technique', '/uploads/docs/plan_cuisine.pdf', '2026-02-01 10:20:00', 1);

-- --------------------------------------------------------

--
-- Structure de la table `entrepreneur`
--

CREATE TABLE `entrepreneur` (
  `id` int(11) NOT NULL,
  `nom` varchar(200) NOT NULL,
  `siret` varchar(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `telephone` varchar(50) NOT NULL,
  `adresse` varchar(250) NOT NULL,
  `ville` varchar(100) NOT NULL,
  `code_postal` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `entrepreneur`
--

INSERT INTO `entrepreneur` (`id`, `nom`, `siret`, `email`, `telephone`, `adresse`, `ville`, `code_postal`) VALUES
(1, 'ABC Plomberie', '12345678900011', 'contact@abc-plomberie.fr', '0140506070', '5 rue du Tuyau', 'Paris', '75010'),
(2, 'Multi-Travaux 69', '98765432100022', 'devis@mt69.fr', '0478001122', '10 rue de la République', 'Lyon', '69000'),
(3, 'Peinture Pro', '55566677700033', 'info@peinturepro.com', '0199887766', '3 villa des Arts', 'Paris', '75018'),
(4, 'Eiffage IDF', '12345678900011', 'contact@eiffage.fr', '0140000001', '1 Rue du Louvre', 'Paris', '75001'),
(5, 'Renov Pro 92', '12345678900022', 'info@renov92.fr', '0140000002', '15 Rue de la Paix', 'Nanterre', '92000'),
(6, 'Bati-Seine', '12345678900033', 'contact@batiseine.fr', '0140000003', '8 Bvd de la Seine', 'Saint-Denis', '93200'),
(7, 'Versailles Bâtiment', '12345678900044', 'v.bat@orange.fr', '0140000004', '30 Rue Royale', 'Versailles', '78000'),
(8, 'Artisan Val-de-Marne', '12345678900055', 'avm@gmail.com', '0140000005', '10 Av de France', 'Créteil', '94000'),
(9, 'Lyon Rénovation', '12345678900066', 'contact@lyon-renov.fr', '0470000001', '5 Rue de la Villette', 'Lyon', '69003'),
(10, 'Rhône Plomberie', '12345678900077', 'rhone.plomb@free.fr', '0470000002', '12 Rue Jean Jaurès', 'Villeurbanne', '69100'),
(11, 'Grand Lyon Élec', '12345678900088', 'elec@grandlyon.fr', '0470000003', '45 Rue de l Est', 'Bron', '69500'),
(12, 'Vénissieux BTP', '12345678900099', 'btp69@pro.fr', '0470000004', '2 Rue du Stade', 'Vénissieux', '69200'),
(13, 'Mont d Or Façade', '12345678900100', 'facade@montdor.fr', '0470000005', '8 Chemin du Moulin', 'Limonest', '69760'),
(14, 'Aquitaine Travaux', '12345678900111', 'aquitaine@travaux.fr', '0550000001', '12 Rue Fondaudège', 'Bordeaux', '33000'),
(15, 'Mérignac Construction', '12345678900122', 'merignac.const@info.fr', '0550000002', '22 Av Kennedy', 'Mérignac', '33700'),
(16, 'Pessac Peinture', '12345678900133', 'peinture@pessac.fr', '0550000003', '5 Rue des Grave', 'Pessac', '33600'),
(17, 'Gironde Énergie', '12345678900144', 'contact@ge.fr', '0550000004', '30 Rue de la Gare', 'Cenon', '33150'),
(18, 'Talence Menuiserie', '12345678900155', 'menuis@talence.fr', '0550000005', '14 Cours Gambetta', 'Talence', '33400');

-- --------------------------------------------------------

--
-- Structure de la table `entrepreneur_categorie`
--

CREATE TABLE `entrepreneur_categorie` (
  `entrepreneur_id` int(11) NOT NULL,
  `categorie_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `entrepreneur_categorie`
--

INSERT INTO `entrepreneur_categorie` (`entrepreneur_id`, `categorie_id`) VALUES
(1, 1),
(2, 1),
(2, 2),
(2, 3),
(3, 3),
(4, 2),
(4, 4),
(5, 3),
(5, 4),
(6, 1),
(6, 4),
(7, 5),
(8, 3),
(9, 1),
(9, 2),
(9, 3),
(9, 4),
(9, 5),
(10, 1),
(11, 2),
(12, 1),
(12, 4),
(13, 3),
(14, 4),
(14, 5),
(15, 4),
(16, 3),
(17, 2),
(18, 5);

-- --------------------------------------------------------

--
-- Structure de la table `inspecteur`
--

CREATE TABLE `inspecteur` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `ville` varchar(100) NOT NULL,
  `code_postal` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `inspecteur`
--

INSERT INTO `inspecteur` (`id`, `nom`, `prenom`, `email`, `telephone`, `adresse`, `ville`, `code_postal`) VALUES
(1, 'Lefebvre', 'Marc', 'm.lefebvre@expert.com', '0788990011', '1 bis rue du Louvre', 'Paris', '75001'),
(2, 'Durand', 'Sophie', 's.durand@expert.com', '0755443322', '20 Place Bellecour', 'Lyon', '69002');

-- --------------------------------------------------------

--
-- Structure de la table `messenger_messages`
--

CREATE TABLE `messenger_messages` (
  `id` bigint(20) NOT NULL,
  `body` longtext NOT NULL,
  `headers` longtext NOT NULL,
  `queue_name` varchar(190) NOT NULL,
  `created_at` datetime NOT NULL,
  `available_at` datetime NOT NULL,
  `delivered_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `photo`
--

CREATE TABLE `photo` (
  `id` int(11) NOT NULL,
  `chemin_fichier` varchar(500) NOT NULL,
  `description` varchar(500) NOT NULL,
  `date_prise` datetime NOT NULL,
  `chantier_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `photo`
--

INSERT INTO `photo` (`id`, `chemin_fichier`, `description`, `date_prise`, `chantier_id`) VALUES
(1, '/uploads/photos/cuisine_avant.jpg', 'État initial cuisine', '2026-02-01 10:15:00', 1),
(2, '/uploads/photos/salon_peinture.jpg', 'Mur à repeindre', '2026-02-15 09:45:00', 2);

-- --------------------------------------------------------

--
-- Structure de la table `prestataire`
--

CREATE TABLE `prestataire` (
  `id` int(11) NOT NULL,
  `libelle` varchar(200) NOT NULL,
  `description` longtext NOT NULL,
  `prix_base` decimal(10,2) NOT NULL,
  `categorie_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `prestataire`
--

INSERT INTO `prestataire` (`id`, `libelle`, `description`, `prix_base`, `categorie_id`) VALUES
(1, 'Pose Robinetterie', 'Remplacement mitigeur cuisine/SDB', 150.00, 1),
(2, 'Installation WC', 'Pose WC suspendu ou classique', 450.00, 1),
(3, 'Tableau Électrique', 'Remise aux normes complète', 1200.00, 2),
(4, 'Peinture Plafond', 'Peinture blanche mate, 2 couches', 25.00, 3),
(5, 'Pose de Parquet', 'Chêne massif collé', 85.00, 5);

-- --------------------------------------------------------

--
-- Structure de la table `prestataire_entrepreneur`
--

CREATE TABLE `prestataire_entrepreneur` (
  `prestataire_id` int(11) NOT NULL,
  `entrepreneur_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL,
  `email` varchar(180) NOT NULL,
  `roles` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL CHECK (json_valid(`roles`)),
  `password` varchar(255) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `telephone` varchar(50) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `ville` varchar(100) DEFAULT NULL,
  `code_postal` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `email`, `roles`, `password`, `nom`, `prenom`, `telephone`, `adresse`, `ville`, `code_postal`) VALUES
(1, 'jean.dupont@email.com', '[\"ROLE_USER\"]', '$2y$13$xyz', 'Dupont', 'Jean', '0601020304', '10 Rue de la Paix', 'Paris', '75002'),
(2, 'marie.curie@email.com', '[\"ROLE_USER\"]', '$2y$13$xyz', 'Curie', 'Marie', '0611223344', '5 Avenue des Sciences', 'Lyon', '69000'),
(3, 'admin@app.com', '[\"ROLE_ADMIN\"]', '$2y$13$xyz', 'System', 'Admin', NULL, NULL, NULL, NULL);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `bien`
--
ALTER TABLE `bien`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_45EDC386FB88E14F` (`utilisateur_id`);

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `chantier`
--
ALTER TABLE `chantier`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_636F27F6BD95B80F` (`bien_id`),
  ADD KEY `IDX_636F27F6B7728AA0` (`inspecteur_id`);

--
-- Index pour la table `devis_entrepreneur`
--
ALTER TABLE `devis_entrepreneur`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_8525C1FE283063EA` (`entrepreneur_id`),
  ADD KEY `IDX_8525C1FED0C0049D` (`chantier_id`);

--
-- Index pour la table `devis_entrepreneur_prestataire`
--
ALTER TABLE `devis_entrepreneur_prestataire`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_76329550A793AE3` (`devis_entrepeneur_id`),
  ADD KEY `IDX_763295509E45C554` (`prestation_id`);

--
-- Index pour la table `devis_type`
--
ALTER TABLE `devis_type`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_C30C36F6D0C0049D` (`chantier_id`);

--
-- Index pour la table `devis_type_entrepreneur`
--
ALTER TABLE `devis_type_entrepreneur`
  ADD PRIMARY KEY (`devis_type_id`,`entrepreneur_id`),
  ADD KEY `IDX_702AC0C557A217CF` (`devis_type_id`),
  ADD KEY `IDX_702AC0C5283063EA` (`entrepreneur_id`);

--
-- Index pour la table `devis_type_prestataire`
--
ALTER TABLE `devis_type_prestataire`
  ADD PRIMARY KEY (`devis_type_id`,`prestataire_id`),
  ADD KEY `IDX_CE06C3D657A217CF` (`devis_type_id`),
  ADD KEY `IDX_CE06C3D6BE3DB2B7` (`prestataire_id`);

--
-- Index pour la table `devis_type_prestation`
--
ALTER TABLE `devis_type_prestation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_2405934557A217CF` (`devis_type_id`),
  ADD KEY `IDX_24059345BE3DB2B7` (`prestataire_id`);

--
-- Index pour la table `doctrine_migration_versions`
--
ALTER TABLE `doctrine_migration_versions`
  ADD PRIMARY KEY (`version`);

--
-- Index pour la table `document`
--
ALTER TABLE `document`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_D8698A76D0C0049D` (`chantier_id`);

--
-- Index pour la table `entrepreneur`
--
ALTER TABLE `entrepreneur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `entrepreneur_categorie`
--
ALTER TABLE `entrepreneur_categorie`
  ADD PRIMARY KEY (`entrepreneur_id`,`categorie_id`),
  ADD KEY `IDX_7B6E3FA0283063EA` (`entrepreneur_id`),
  ADD KEY `IDX_7B6E3FA0BCF5E72D` (`categorie_id`);

--
-- Index pour la table `inspecteur`
--
ALTER TABLE `inspecteur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `messenger_messages`
--
ALTER TABLE `messenger_messages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_75EA56E0FB7336F0` (`queue_name`),
  ADD KEY `IDX_75EA56E0E3BD61CE` (`available_at`),
  ADD KEY `IDX_75EA56E016BA31DB` (`delivered_at`);

--
-- Index pour la table `photo`
--
ALTER TABLE `photo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_14B78418D0C0049D` (`chantier_id`);

--
-- Index pour la table `prestataire`
--
ALTER TABLE `prestataire`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_60A26480BCF5E72D` (`categorie_id`);

--
-- Index pour la table `prestataire_entrepreneur`
--
ALTER TABLE `prestataire_entrepreneur`
  ADD PRIMARY KEY (`prestataire_id`,`entrepreneur_id`),
  ADD KEY `IDX_5B1F8793BE3DB2B7` (`prestataire_id`),
  ADD KEY `IDX_5B1F8793283063EA` (`entrepreneur_id`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_IDENTIFIER_EMAIL` (`email`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `bien`
--
ALTER TABLE `bien`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT pour la table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `chantier`
--
ALTER TABLE `chantier`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT pour la table `devis_entrepreneur`
--
ALTER TABLE `devis_entrepreneur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `devis_entrepreneur_prestataire`
--
ALTER TABLE `devis_entrepreneur_prestataire`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `devis_type`
--
ALTER TABLE `devis_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT pour la table `devis_type_prestation`
--
ALTER TABLE `devis_type_prestation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT pour la table `document`
--
ALTER TABLE `document`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `entrepreneur`
--
ALTER TABLE `entrepreneur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT pour la table `inspecteur`
--
ALTER TABLE `inspecteur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `messenger_messages`
--
ALTER TABLE `messenger_messages`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `photo`
--
ALTER TABLE `photo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `prestataire`
--
ALTER TABLE `prestataire`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `bien`
--
ALTER TABLE `bien`
  ADD CONSTRAINT `FK_45EDC386FB88E14F` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `chantier`
--
ALTER TABLE `chantier`
  ADD CONSTRAINT `FK_636F27F6B7728AA0` FOREIGN KEY (`inspecteur_id`) REFERENCES `inspecteur` (`id`),
  ADD CONSTRAINT `FK_636F27F6BD95B80F` FOREIGN KEY (`bien_id`) REFERENCES `bien` (`id`);

--
-- Contraintes pour la table `devis_entrepreneur`
--
ALTER TABLE `devis_entrepreneur`
  ADD CONSTRAINT `FK_8525C1FE283063EA` FOREIGN KEY (`entrepreneur_id`) REFERENCES `entrepreneur` (`id`),
  ADD CONSTRAINT `FK_8525C1FED0C0049D` FOREIGN KEY (`chantier_id`) REFERENCES `chantier` (`id`);

--
-- Contraintes pour la table `devis_entrepreneur_prestataire`
--
ALTER TABLE `devis_entrepreneur_prestataire`
  ADD CONSTRAINT `FK_763295509E45C554` FOREIGN KEY (`prestation_id`) REFERENCES `prestataire` (`id`),
  ADD CONSTRAINT `FK_76329550A793AE3` FOREIGN KEY (`devis_entrepeneur_id`) REFERENCES `devis_entrepreneur` (`id`);

--
-- Contraintes pour la table `devis_type`
--
ALTER TABLE `devis_type`
  ADD CONSTRAINT `FK_C30C36F6D0C0049D` FOREIGN KEY (`chantier_id`) REFERENCES `chantier` (`id`);

--
-- Contraintes pour la table `devis_type_entrepreneur`
--
ALTER TABLE `devis_type_entrepreneur`
  ADD CONSTRAINT `FK_702AC0C5283063EA` FOREIGN KEY (`entrepreneur_id`) REFERENCES `entrepreneur` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_702AC0C557A217CF` FOREIGN KEY (`devis_type_id`) REFERENCES `devis_type` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `devis_type_prestataire`
--
ALTER TABLE `devis_type_prestataire`
  ADD CONSTRAINT `FK_CE06C3D657A217CF` FOREIGN KEY (`devis_type_id`) REFERENCES `devis_type` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_CE06C3D6BE3DB2B7` FOREIGN KEY (`prestataire_id`) REFERENCES `prestataire` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `devis_type_prestation`
--
ALTER TABLE `devis_type_prestation`
  ADD CONSTRAINT `FK_2405934557A217CF` FOREIGN KEY (`devis_type_id`) REFERENCES `devis_type` (`id`),
  ADD CONSTRAINT `FK_24059345BE3DB2B7` FOREIGN KEY (`prestataire_id`) REFERENCES `prestataire` (`id`);

--
-- Contraintes pour la table `document`
--
ALTER TABLE `document`
  ADD CONSTRAINT `FK_D8698A76D0C0049D` FOREIGN KEY (`chantier_id`) REFERENCES `chantier` (`id`);

--
-- Contraintes pour la table `entrepreneur_categorie`
--
ALTER TABLE `entrepreneur_categorie`
  ADD CONSTRAINT `FK_7B6E3FA0283063EA` FOREIGN KEY (`entrepreneur_id`) REFERENCES `entrepreneur` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_7B6E3FA0BCF5E72D` FOREIGN KEY (`categorie_id`) REFERENCES `categorie` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `photo`
--
ALTER TABLE `photo`
  ADD CONSTRAINT `FK_14B78418D0C0049D` FOREIGN KEY (`chantier_id`) REFERENCES `chantier` (`id`);

--
-- Contraintes pour la table `prestataire`
--
ALTER TABLE `prestataire`
  ADD CONSTRAINT `FK_60A26480BCF5E72D` FOREIGN KEY (`categorie_id`) REFERENCES `categorie` (`id`);

--
-- Contraintes pour la table `prestataire_entrepreneur`
--
ALTER TABLE `prestataire_entrepreneur`
  ADD CONSTRAINT `FK_5B1F8793283063EA` FOREIGN KEY (`entrepreneur_id`) REFERENCES `entrepreneur` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_5B1F8793BE3DB2B7` FOREIGN KEY (`prestataire_id`) REFERENCES `prestataire` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
