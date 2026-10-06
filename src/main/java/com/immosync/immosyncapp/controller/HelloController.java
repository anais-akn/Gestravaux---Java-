package com.immosync.immosyncapp.controller;

import com.immosync.immosyncapp.dto.MoisStatsDTO;
import com.immosync.immosyncapp.dto.PrestaStatsDTO;
import com.immosync.immosyncapp.dto.VilleStatsDTO;
import com.immosync.immosyncapp.entities.*;
import com.immosync.immosyncapp.services.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

@Component
public class HelloController implements Initializable{
    public AnchorPane paneConnexion;
    public AnchorPane paneAcceuil;
    public AnchorPane paneListBien;
    public AnchorPane paneCreaBien;
    public AnchorPane paneCreaChantier;
    public AnchorPane paneCreaDevisType;
    public TableView bienTable;
    public TableColumn colId;
    public TableColumn colCp;
    public TableColumn colAdresse;
    public TextField adresseField;
    public TextField villeField;
    public ComboBox<Utilisateur> proprietaireCombo;
    public ComboBox<Bien> bienCombo;
    public ComboBox<Inspecteur> inspecteurCombo;
    public ComboBox<Categorie> categorieCombo;
    public ComboBox<Prestataire> prestataireCombo;
    public Spinner quantitySpinner;
    public TableView prestationTable;
    public TableColumn colPrestation;
    public TableColumn colQuantite;
    public TableColumn colPrixUnitaire;
    public TableColumn colTotal;
    public Label labelTotalFinal;
    public AnchorPane paneModifierBien;
    public TextField txtAdresse;
    public TextField txtVSurface;
    public Label tctVille;
    public TextField txtVille;
    public TextField txtCodePostal;
    public ComboBox<Utilisateur> modifProprietaireCombo;
    public ComboBox<Bien> cboBienAModif;
    public TextField txtSurface;
    public TextField txtCp;
    public Label lblId;
    public Label lblMdp;
    public Label lblSuppr;
    public AnchorPane paneDashboard;
    public BarChart bcChantierVille;
    public LineChart lcChantierMois;
    public PieChart pcRevenusPresta;
    public Label lblNbUtil;
    public Label lblEntr;
    public Label lblProprio;
    public Label lblInspec;
    public AnchorPane paneEntrepreneur;
    public ListView lvEntrepreneurs;
    private DevisType devisEnCours;
    private Set<Entrepreneur> entrepreneursChoisis = new HashSet<>();
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField mdpField;

    private Integer idBienActuel;
    private Map<Prestataire, Integer> prestationsSelectionnees = new HashMap<>();
    private final ChantierService chantierService;
    private final UtilisateurService userService;
    private final BienService bienService;
    private final InspecteurService inspecteurService;
    private final CategorieService categorieService;
    private final PrestataireService prestataireService;
    private boolean suppressionActif = false;

    public HelloController(ChantierService chantierService, UtilisateurService userService, BienService bienService, InspecteurService inspecteurService, CategorieService categorieService, PrestataireService prestataireService) {
        this.chantierService = chantierService;
        this.userService = userService;
        this.bienService = bienService;
        this.inspecteurService = inspecteurService;
        this.categorieService = categorieService;
        this.prestataireService = prestataireService;
    }
    public void changeImageViewImg(ImageView imgView, String linkImage){
        imgView.setImage(
                new Image(
                        getClass().getResource(
                                "/images/"+linkImage
                        ).toExternalForm()
                )
        );
    }

    public void invisible(AnchorPane apCourante){apCourante.setVisible(false);return;}
    public void visible(AnchorPane apCourante){apCourante.setVisible(true);return;}

    public void clearAll()
    {
        invisible(paneAcceuil);
        invisible(paneConnexion);
        invisible(paneCreaBien);
        invisible(paneCreaChantier);
        invisible(paneCreaDevisType);
        invisible(paneListBien);
        invisible(paneModifierBien);
        invisible(paneDashboard);
        invisible(paneEntrepreneur);
    }
    private <T> void setComboConverter(ComboBox<T> combo, java.util.function.Function<T, String> mapper) {
        combo.setConverter(new javafx.util.StringConverter<T>() {
            @Override
            public String toString(T object) {
                return (object == null) ? "" : mapper.apply(object);
            }
            @Override
            public T fromString(String string) {
                return null;
            }
        });
    }

    public boolean handleLogin(String email,String mdp) {
        if (emailField.getText().isEmpty()) {
            lblId.setText("entrez un identifiant");
            return false;
        }
        if (mdpField.getText().isEmpty()) {
            lblMdp.setText("entrez un mot de passe");
            return false;
        }
        Utilisateur user = userService.existeUser(email, mdp);
        if (user != null) {
            return true;
        } else {
            afficherAlerte("Champ incorecte(s)", "Mot de passe et/ou identifiant incorrecte(s)", Alert.AlertType.WARNING);
            return false;
        }
    }

    public void chargerDonnees(Bien bien) {
        this.idBienActuel = bien.getId();
        txtAdresse.setText(bien.getAdresse());
        txtVille.setText(bien.getVille());
        txtCodePostal.setText(bien.getCodePostal());
        txtVSurface.setText(bien.getSurface().toString()!= null ? bien.getSurface().toString() : "");
        if (bien.getUtilisateur() != null) {
            modifProprietaireCombo.setValue(bien.getUtilisateur());
        } else {
            modifProprietaireCombo.getSelectionModel().clearSelection();
        }
    }

    private void handleValiderModif(String adresse, String ville, String cp, BigDecimal surface, Integer proprietaire) {
        try {
            bienService.modifierBien(
                    idBienActuel,
                    adresse,
                    ville,
                    cp,
                    surface,
                    proprietaire
            );
            System.out.println("Modification réussie !");
            retourAccueil(null);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Le bien a été mis à jour !");
            alert.show();

        } catch (Exception e) {
            System.err.println("Erreur lors de la modif : " + e.getMessage());
        }
    }

    private DevisType handleValiderChantier(String description, Bien bien, Inspecteur inspec) {
        try {
            DevisType devis = chantierService.creerChantierComplet(
                    description,
                    bien,
                    inspec,
                    prestationsSelectionnees
            );
            System.out.println("Tout a été créé en base !");
            return devis;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private void initPageDevis() {
        categorieCombo.setItems(FXCollections.observableArrayList(categorieService.getAllCategories()));
        prestataireCombo.setDisable(true);
        prestataireCombo.getItems().clear();
        categorieCombo.setOnAction(e -> {
            Categorie cat = categorieCombo.getValue();
            if (cat != null) {
                prestataireCombo.setDisable(false);
                List<Prestataire> filtres = prestataireService.getPrestatairesByCategorie(cat);
                prestataireCombo.setItems(FXCollections.observableArrayList(filtres));
                prestataireCombo.getSelectionModel().selectFirst();
            }
        });
        prestationsSelectionnees.clear();
        prestationTable.getItems().clear();
        labelTotalFinal.setText("0.00 €");
    }

    private void calculerTotalGlobal() {
        BigDecimal total = BigDecimal.ZERO;
        for (Object item : prestationTable.getItems()) {
            LigneDevis ligne = (LigneDevis) item;
            total = total.add(ligne.getTotal());
        }
        labelTotalFinal.setText(String.format("%.2f €", total));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCp.setCellValueFactory(new PropertyValueFactory<>("codePostal"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        colPrestation.setCellValueFactory(new PropertyValueFactory<>("nomPrestation"));
        colQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        colPrixUnitaire.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        quantitySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));

        setComboConverter(proprietaireCombo, u -> u.getNom() + " " + u.getPrenom());
        setComboConverter(modifProprietaireCombo, u -> u.getNom() + " " + u.getPrenom());

        setComboConverter(bienCombo, b -> b.getAdresse() + " (" + b.getVille() + ")");
        setComboConverter(cboBienAModif, b -> b.getAdresse());

        setComboConverter(inspecteurCombo, i -> i.getNom() + " " + i.getPrenom());

        setComboConverter(categorieCombo, c -> c.getLibelle());
        setComboConverter(prestataireCombo, p -> p.getLibelle() + " (" + p.getPrixBase() + "€)");
        lblId.setText("");
        lblMdp.setText("");
        clearAll();
        visible(paneConnexion);
        lblSuppr.setText("");
    }

    public void clicConnection(MouseEvent mouseEvent) {
        if(handleLogin(emailField.getText(),mdpField.getText())){
            clearAll();
            visible(paneAcceuil);
        }
    }

    public void goToBiens(MouseEvent mouseEvent) {
        clearAll();
        visible(paneListBien);
        List<Bien> listeBiens = bienService.getAllBiens();
        ObservableList<Bien> observableListe = FXCollections.observableArrayList(listeBiens);
        bienTable.setItems(observableListe);
        bienTable.refresh();
    }

    public void goToChantier(MouseEvent mouseEvent) {
        clearAll();
        visible(paneCreaChantier);

        List<Bien> tousLesBiens = bienService.getAllBiens();
        bienCombo.setItems(FXCollections.observableArrayList(tousLesBiens));

        List<Inspecteur> tousLesInspecteurs = inspecteurService.getAllInspecteurs();
        inspecteurCombo.setItems(FXCollections.observableArrayList(tousLesInspecteurs));

        bienCombo.getSelectionModel().clearSelection();
        inspecteurCombo.getSelectionModel().clearSelection();
    }

    public void clicDeconnexion(MouseEvent mouseEvent) {
        clearAll();
        visible(paneConnexion);
    }

    public void retourAccueil(MouseEvent actionEvent) {
        clearAll();
        visible(paneAcceuil);
    }

    public void ouvrirCreationBien(MouseEvent actionEvent) {
        clearAll();
        visible(paneCreaBien);
        List<Utilisateur> tousLesUsers = userService.getAllUsers();
        proprietaireCombo.setItems(FXCollections.observableArrayList(tousLesUsers));
        adresseField.clear();
        villeField.clear();
        txtSurface.clear();
        txtCp.clear();
        proprietaireCombo.getSelectionModel().clearSelection();
    }
    public void modifierBien(MouseEvent actionEvent) {
        clearAll();
        visible(paneModifierBien);
        cboBienAModif.setItems(FXCollections.observableArrayList(bienService.getAllBiensAvecProprios()));

        List<Utilisateur> tousLesUsers = userService.getAllUsers();
        modifProprietaireCombo.setItems(FXCollections.observableArrayList(tousLesUsers));

        cboBienAModif.setOnAction(e -> {
            Bien selectionne = (Bien) cboBienAModif.getValue();
            if (selectionne != null) {
                this.idBienActuel = selectionne.getId();
                chargerDonnees(selectionne);
            }
        });
    }
    public void handleEnregistrer(MouseEvent actionEvent) {
        try {
            String adresse = adresseField.getText().trim();
            String ville = villeField.getText().trim();
            String cp = txtCp.getText().trim();
            String surfaceRaw = txtSurface.getText().replace(",", ".").trim();
            Utilisateur proprioSelectionne = (Utilisateur) proprietaireCombo.getValue();

            if (adresse.isEmpty()) {
                afficherAlerte("Champ incomplet", "Veuillez remplir le champ de l'adresse.", Alert.AlertType.WARNING);
                return;
            }
            if (ville.isEmpty()) {
                afficherAlerte("Champ incomplet", "Veuillez remplir le champ de la ville.", Alert.AlertType.WARNING);
                return;
            }
            if ( cp.isEmpty() ) {
                afficherAlerte("Champ incomplet", "Veuillez remplir le champ du code postal.", Alert.AlertType.WARNING);
                return;
            }
            if (surfaceRaw.isEmpty() ) {
                afficherAlerte("Champ incomplet", "Veuillez remplir le champ de la surface.", Alert.AlertType.WARNING);
                return;
            }
            if ( proprioSelectionne == null) {
                afficherAlerte("Champ incomplet", "Veuillez sélectionner un propriétaire.", Alert.AlertType.WARNING);
                return;
            }

            BigDecimal surface;
            try {
                surface = new BigDecimal(surfaceRaw);
                if (surface.compareTo(BigDecimal.ZERO) <= 0) {
                    afficherAlerte("Valeur incorrecte", "La surface doit être supérieure à 0.", Alert.AlertType.ERROR);
                    return;
                }
            } catch (NumberFormatException e) {
                afficherAlerte("Format invalide", "La surface doit être un nombre valide (ex: 75.5).", Alert.AlertType.ERROR);
                return;
            }

            if (!cp.matches("\\d{5}")) {
                afficherAlerte("Code Postal invalide", "Le code postal doit contenir exactement 5 chiffres.", Alert.AlertType.ERROR);
                return;
            }

            bienService.creerBien(adresse, ville, cp, surface, proprioSelectionne);

            afficherAlerte("Succès", "Le bien a été créé avec succès !", Alert.AlertType.INFORMATION);

            ListeBiens(actionEvent);

        } catch (Exception e) {
            afficherAlerte("Erreur système", "Une erreur inattendue est survenue : " + e.getMessage(), Alert.AlertType.ERROR);
            System.err.println("Erreur lors de la création : " + e.getMessage());
        }
    }

    private void afficherAlerte(String titre, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void handleCreerDevis(MouseEvent mouseEvent) {
        Bien bienChoisi = (Bien) bienCombo.getValue();
        Inspecteur inspecteurChoisi = (Inspecteur) inspecteurCombo.getValue();

        if (bienChoisi == null || inspecteurChoisi == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un bien et un inspecteur.");
            alert.showAndWait();
            return;
        }
        clearAll();
        visible(paneCreaDevisType);
        initPageDevis();
    }
    public void validerDevis(MouseEvent mouseEvent) {
        if (prestationsSelectionnees.isEmpty()) return;

        Bien bienSelectionne = (Bien) bienCombo.getValue();

        List<Prestataire> listePrestataires = new ArrayList<>(prestationsSelectionnees.keySet());

        List<Entrepreneur> eligibles = chantierService.getEntrepreneursPotentiels(bienSelectionne, listePrestataires);

        if (eligibles.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Disponibilité");
            alert.setHeaderText("Aucun entrepreneur trouvé");
            alert.setContentText("Il n'y a aucun entrepreneur éligible (catégorie ou distance) pour ce chantier.");
            alert.showAndWait();
            return;
        }

        devisEnCours = handleValiderChantier(
                "Chantier créé via l'app",
                bienSelectionne,
                (Inspecteur) inspecteurCombo.getValue()
        );

        if (devisEnCours != null) {
            remplirListeEntrepreneurs(eligibles);
            visible(paneEntrepreneur);
        }
    }

    private void remplirListeEntrepreneurs(List<Entrepreneur> eligibles) {
        entrepreneursChoisis.clear();
        lvEntrepreneurs.setItems(FXCollections.observableArrayList(eligibles));
        lvEntrepreneurs.setCellFactory(CheckBoxListCell.<Entrepreneur>forListView(ent -> {
            BooleanProperty selected = new SimpleBooleanProperty();
            selected.set(entrepreneursChoisis.contains(ent));
            selected.addListener((obs, wasSelected, isNowSelected) -> {
                if (isNowSelected) {
                    entrepreneursChoisis.add(ent);
                } else {
                    entrepreneursChoisis.remove(ent);
                }
            });
            return selected;
        }, new StringConverter<Entrepreneur>() {
            @Override
            public String toString(Entrepreneur e) {
                return (e == null) ? "" : e.getNom() + " (" + e.getVille() + ")";
            }
            @Override
            public Entrepreneur fromString(String s) {
                return null;
            }
        }));
    }

    public void envoyerDevis(MouseEvent mouseEvent) {
        if (entrepreneursChoisis.isEmpty()) {
            return;
        }

        chantierService.lierEntrepreneursAuDevis(devisEnCours.getId(), new ArrayList<>(entrepreneursChoisis));

        System.out.println("Devis envoyé avec succès !");
        retourAccueil(mouseEvent);
    }

    public void ListeBiens(MouseEvent mouseEvent) {
        clearAll();
        visible(paneListBien);
        List<Bien> listeBiens = bienService.getAllBiens();
        ObservableList<Bien> observableListe = FXCollections.observableArrayList(listeBiens);
        bienTable.setItems(observableListe);
        bienTable.refresh();
    }

    public void EnregistrerModif(MouseEvent mouseEvent) {
        try {
            if (idBienActuel == null) {
                System.err.println("Aucun bien sélectionné !");
                return;
            }
            String surfaceRaw = txtVSurface.getText().trim();
            surfaceRaw = surfaceRaw.replace(",", ".");
            if (surfaceRaw.isEmpty()) {
                System.err.println("La surface ne peut pas être vide");
                return;
            }
            BigDecimal surfaceBien = new BigDecimal(surfaceRaw);
            Utilisateur nouveauProprio = (Utilisateur) modifProprietaireCombo.getValue();
            Integer idProprio = (nouveauProprio != null) ? nouveauProprio.getId() : null;
            handleValiderModif(txtAdresse.getText(),txtVille.getText(),txtCodePostal.getText(),surfaceBien,idProprio);
            System.out.println("Modification réussie !");

        } catch (NumberFormatException e) {
            System.err.println("Erreur : La surface doit être un nombre valide (ex: 120.50)");
        }
    }

    public void validerSelection(MouseEvent mouseEvent) {
        Prestataire p = (Prestataire) prestataireCombo.getValue();
        Integer qte = (Integer) quantitySpinner.getValue();

        if (p != null && qte > 0) {
            prestationsSelectionnees.put(p, qte);
            LigneDevis ligne = new LigneDevis(p, qte);
            prestationTable.getItems().add(ligne);
            calculerTotalGlobal();
        }
    }

    public void supprDevis(MouseEvent mouseEvent) {
        if (prestationTable.getItems().isEmpty()) {
            lblSuppr.setText("Le tableau est déjà vide !");
            return;
        }

        suppressionActif = true;
        lblSuppr.setText("Choisissez une ligne à supprimer");
    }

    public void clickTablePrestation(MouseEvent mouseEvent) {
        if (!suppressionActif) {
            return;
        }
        LigneDevis ligneSelectionnee = (LigneDevis) prestationTable.getSelectionModel().getSelectedItem();

        if (ligneSelectionnee != null) {
            Prestataire aSupprimer = null;
            for (Prestataire p : prestationsSelectionnees.keySet()) {
                if (p.getLibelle().equals(ligneSelectionnee.getNomPrestation())) {
                    aSupprimer = p;
                    break;
                }
            }
            if (aSupprimer != null) {
                prestationsSelectionnees.remove(aSupprimer);
                prestationTable.getItems().remove(ligneSelectionnee);
                calculerTotalGlobal();
                suppressionActif = false;
                lblSuppr.setText("");
                prestationTable.getSelectionModel().clearSelection();
            }
        }
    }
    public void chargerGraphiqueVilles() {
        bcChantierVille.setAnimated(false);
        bcChantierVille.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Répartition par ville");
        List<VilleStatsDTO> stats = chantierService.getStatsVilles();
        for (VilleStatsDTO stat : stats) {
            series.getData().add(new XYChart.Data<>(stat.ville(), stat.nombreChantiers()));
        }
        bcChantierVille.getData().add(series);
    }

    public void chargerGraphiqueEvolution() {
        lcChantierMois.setAnimated(false);
        lcChantierMois.getData().clear();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Nouveaux chantiers par mois");

        List<MoisStatsDTO> stats = chantierService.getStatsMensuelles();
        for (MoisStatsDTO stat : stats) {
            series.getData().add(new XYChart.Data<>(stat.mois(), stat.nombreChantiers()));
        }
        lcChantierMois.getData().add(series);
    }
    public void chargerGraphiqueRevenus() {
        pcRevenusPresta.setAnimated(false);
        pcRevenusPresta.setLegendVisible(false);
        ObservableList<PieChart.Data> nouveauxElements = FXCollections.observableArrayList();
        List<PrestaStatsDTO> stats = chantierService.getStatsRevenus();
        for (PrestaStatsDTO stat : stats) {
            nouveauxElements.add(new PieChart.Data(
                    stat.categorieLibelle() + " (" + String.format("%.0f", stat.totalRevenus()) + "€)",
                    stat.totalRevenus()
            ));
        }
        pcRevenusPresta.getData().clear();
        pcRevenusPresta.setData(nouveauxElements);
        pcRevenusPresta.setLabelsVisible(true);
        pcRevenusPresta.setClockwise(true);
        pcRevenusPresta.setStartAngle(90);
        pcRevenusPresta.requestLayout();
    }

    public void goToDashboard(MouseEvent mouseEvent) {
        chargerStatsUtilisateurs();
        chargerGraphiqueVilles();
        chargerGraphiqueEvolution();
        chargerGraphiqueRevenus();
        visible(paneDashboard);
    }

    public void chargerStatsUtilisateurs() {
        Map<String, Object> stats = userService.getDashboardUserStats();

        lblNbUtil.setText(stats.get("total").toString());

        lblProprio.setText(String.format("%.1f%%", (Double) stats.get("pctProprio")));
        lblEntr.setText(String.format("%.1f%%", (Double) stats.get("pctEntr")));
        lblInspec.setText(String.format("%.1f%%", (Double) stats.get("pctInspec")));
    }


    public static class LigneDevis {
        private final Prestataire prestataire;
        private final int quantite;
        private final BigDecimal total;

        public LigneDevis(Prestataire p, int qte) {
            this.prestataire = p;
            this.quantite = qte;
            this.total = p.getPrixBase().multiply(new BigDecimal(qte));
        }

        public String getNomPrestation() {
            return prestataire.getLibelle();
        }

        public String getCategorie() {
            if (prestataire.getCategorie() != null) {
                return prestataire.getCategorie().getLibelle();
            }
            return "N/A";
        }
        public BigDecimal getTotal() { return total; }
    }
}




