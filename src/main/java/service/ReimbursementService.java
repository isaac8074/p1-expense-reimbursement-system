package service;

import models.Reimbursement;
import repository.ReimbursementRepository;

import java.util.List;

public class ReimbursementService {
    private ReimbursementRepository reimbursementRepository;

    public ReimbursementService()   {
        this.reimbursementRepository = new ReimbursementRepository();
    }

    public List<Reimbursement> findAll() {
        return this.reimbursementRepository.findAll();
    }

    public List<Reimbursement> findName(String name) {
        return this.reimbursementRepository.findName(name);
    }

    public void save(Reimbursement reimbursement)   {
        this.reimbursementRepository.save(reimbursement);
    }

    public void approve(int id) {
        this.reimbursementRepository.approve(id);
    }

    public void deny(int id)    {
        this.reimbursementRepository.deny(id);
    }
}
