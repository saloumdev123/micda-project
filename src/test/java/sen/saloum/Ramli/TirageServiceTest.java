//package sen.saloum.Ramli;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockedStatic;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import sen.saloum.Ramli.dto.tirage.TirageDto;
//import sen.saloum.Ramli.enums.NomFigureBase;
//import sen.saloum.Ramli.enums.TypeFigure;
//import sen.saloum.Ramli.mapStruct.TirageMapper;
//import sen.saloum.Ramli.models.Tirage;
//import sen.saloum.Ramli.models.Utilisateur;
//import sen.saloum.Ramli.repos.TirageRepository;
//import sen.saloum.Ramli.repos.UtilisateurRepository;
//import sen.saloum.Ramli.service.TirageService;
//import sen.saloum.Ramli.service.loader.InterpretationLoader;
//import sen.saloum.Ramli.utils.FigureUtils;
//import sen.saloum.Ramli.utils.NomFigureUtils;
//
//import java.util.Optional;
//
//import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.mockStatic;
//import static org.mockito.Mockito.when;
//
//
//@SpringBootTest
//class TirageServiceTest {
//
//
//    @Mock
//    private UtilisateurRepository utilisateurRepository;
//
//    @Mock
//    private TirageRepository tirageRepository;
//
//    @Mock
//    private InterpretationLoader interpretationLoader;
//
//    @Mock
//    private TirageMapper tirageMapper;
//
//    @InjectMocks
//    private TirageService tirageService;
//
//    @BeforeEach
//    void setup() {
//        if (!utilisateurRepository.existsById(1L)) {
//            Utilisateur u = new Utilisateur();
//            u.setId(1L);
//            u.setNom("Utilisateur Test");
//            u.setEmail("test@example.com");
//            u.setMotDePasse("password");
//            utilisateurRepository.save(u);
//        }
//    }
//    @Test
//    void testGenererEtEnregistrerTirage() {
//        try (MockedStatic<FigureUtils> mockedFigureUtils = mockStatic(FigureUtils.class)) {
//            // Mock de creerFigureComposee : renvoie simplement le premier argument pour simplifier
//            mockedFigureUtils.when(() -> FigureUtils.creerFigureComposee(any(), any()))
//                    .thenAnswer(invocation -> invocation.getArgument(0));
//            Utilisateur user = new Utilisateur();
//            user.setId(1L);
//            user.setNom("Test User");
//            when(utilisateurRepository.findById(1L)).thenReturn(Optional.of(user));
//            when(tirageRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);
//            when(interpretationLoader.getInterpretation(any(), any())).thenReturn(Optional.of("Interprétation test"));
//
//            TirageDto dto = new TirageDto();
//            dto.setQuestion("Question test");
//            dto.setNomFigureBase(NomFigureBase.VIA);
//            dto.setTypeFigure(TypeFigure.TEMOIN_1);
//            Tirage tirage = tirageService.genererTirageDynamique(1L, dto);
//            assertNotNull(tirage.getFigures());
//            assertTrue(tirage.getFigures().size() >= 12, "Doit avoir au moins 12 figures");
//        }
//    }
//
//}