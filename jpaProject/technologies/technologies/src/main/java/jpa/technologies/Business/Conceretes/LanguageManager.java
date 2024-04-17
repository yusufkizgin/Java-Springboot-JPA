package jpa.technologies.Business.Conceretes;

import jpa.technologies.Business.Abstracts.LanguageService;
import jpa.technologies.Business.Request.CreateLanguageRequest;
import jpa.technologies.Business.Request.DeleteLanguageRequest;
import jpa.technologies.Business.Request.UpdateLanguageRequest;
import jpa.technologies.Business.Response.GetAllLanguagesResponse;
import jpa.technologies.Business.Response.GetAllTechnologiesResponse;
import jpa.technologies.Business.Response.GetTechnologiesByLanguageResponse;
import jpa.technologies.DataAccess.Abstracts.LanguageRepository;
import jpa.technologies.Entities.Conceretes.ProgrammingLanguage;
import jpa.technologies.Entities.Conceretes.Technology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LanguageManager implements LanguageService {
    LanguageRepository languageRepository;

    @Autowired
    public LanguageManager(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public List<GetAllLanguagesResponse> getAllLanguages() {
        return LanguageMapper(languageRepository.findAll());
    }

    @Override
    public void add(CreateLanguageRequest createLanguageRequest) {
        ProgrammingLanguage language = new ProgrammingLanguage();
        language.setName(createLanguageRequest.getName());
        this.languageRepository.save(language);
    }
    @Override
    public void update(UpdateLanguageRequest updateLanguageRequest) {
        ProgrammingLanguage programmingLanguage = new ProgrammingLanguage();
        programmingLanguage.setName(updateLanguageRequest.getName());
        programmingLanguage.setId(updateLanguageRequest.getId());
        languageRepository.save(programmingLanguage);
   }

    @Override
    public void remove(DeleteLanguageRequest deleteLanguageRequest) {
        List<ProgrammingLanguage> languages = languageRepository.findAll();
        for (ProgrammingLanguage l : languages){
            if(deleteLanguageRequest.getId() == l.getId()){
                languageRepository.delete(l);
            }
        }
    }

    @Override
    public ProgrammingLanguage getTechnologiesByLanguage(int id) {
        return languageRepository.findById(id).orElse(null);
    }

    private List<GetAllLanguagesResponse> LanguageMapper(List<ProgrammingLanguage> languages){
        List<GetAllLanguagesResponse> response = new ArrayList<GetAllLanguagesResponse>();
        for (ProgrammingLanguage request : languages) {
            GetAllLanguagesResponse getAllLanguagesResponse = new GetAllLanguagesResponse();
            getAllLanguagesResponse.setId(request.getId());
            getAllLanguagesResponse.setName(request.getName());

            response.add(getAllLanguagesResponse);
        }
        return response;
    }
}
