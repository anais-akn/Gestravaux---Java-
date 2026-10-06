package com.immosync.immosyncapp.dto;

import java.util.List;

public class DevisTypeSelectionDTO {
    private Integer devisTypeId;
    private String intitule;
    private List<String> categoriesRequises;
    private List<Integer> entrepreneursEligiblesIds;

    public List<String> getCategoriesRequises() {
        return categoriesRequises;
    }

    public void setCategoriesRequises(List<String> categoriesRequises) {
        this.categoriesRequises = categoriesRequises;
    }

    public Integer getDevisTypeId() {
        return devisTypeId;
    }

    public List<Integer> getEntrepreneursEligiblesIds() {
        return entrepreneursEligiblesIds;
    }

    public void setEntrepreneursEligiblesIds(List<Integer> entrepreneursEligiblesIds) {
        this.entrepreneursEligiblesIds = entrepreneursEligiblesIds;
    }

    public void setDevisTypeId(Integer devisTypeId) {
        this.devisTypeId = devisTypeId;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
}