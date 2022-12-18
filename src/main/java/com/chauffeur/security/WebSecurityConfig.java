package com.chauffeur.security;

import com.chauffeur.security.jwt.JwtAuthEntryPoint;
import com.chauffeur.security.jwt.JwtAuthTokenFilter;
import com.chauffeur.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SuppressWarnings("deprecation")

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers("/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                        .cors()
                                .disable()
                                        .authorizeRequests()

                .antMatchers("/**/auth/signUp").permitAll()
                .antMatchers("/**/auth/authenticated").permitAll()

                .antMatchers("/**/chauffeurs/create").permitAll()
                .antMatchers("/**/chauffeurs/createWithFiles").permitAll()
                .antMatchers("/**/chauffeurs/createWithFilesInFolder").permitAll()
                .antMatchers("/**/chauffeurs/update/*").permitAll()
                .antMatchers("/**/chauffeurs/all").permitAll()
                .antMatchers("/**/chauffeurs/searchChauffeurOrderByIdDesc").permitAll()
                .antMatchers("/**/chauffeurs/searchChauffeurBySelectedIsTrue").permitAll()
                .antMatchers("/**/chauffeurs/**").permitAll()
                .antMatchers("/**/chauffeurs/searchChauffeursByPermis/**").permitAll()
                .antMatchers("/**/chauffeurs/searchChauffeurByKeyword/**").permitAll()
                .antMatchers("/**/chauffeurs/searchChauffeurByDisponibilite/**").permitAll()
                .antMatchers("/**/chauffeurs/searchChauffeurByPageables/**").permitAll()
                .antMatchers("/**/chauffeurs/photoChauffeur/**").permitAll()
                .antMatchers("/**/chauffeurs/photoChauffeurInFolder/{id}").permitAll()
                .antMatchers("/**/chauffeurs/uploadChauffeurPhoto/{id}").permitAll()
                .antMatchers("/**/chauffeurs/uploadChauffeurPhotoInFolder/{id}").permitAll()
                .antMatchers("/**/chauffeurs/cvChauffeur/**").permitAll()
                .antMatchers("/**/chauffeurs/cvChauffeurInFolder/{id}").permitAll()
                .antMatchers("/**/chauffeurs/uploadChauffeurCv/{id}").permitAll()
                .antMatchers("/**/chauffeurs/uploadChauffeurCvInFolder/{id}").permitAll()
                .antMatchers("/**/chauffeurs/downloadContratFile/**").permitAll()
                .antMatchers("/**/chauffeurs/downloadCvFile/**").permitAll()
                .antMatchers("/**/chauffeurs/NumbersOfChauffeurs").permitAll()
                .antMatchers("/**/chauffeurs/numberOfChauffeurPeerMonth").permitAll()
                .antMatchers("/**/chauffeurs/numberOfChauffeurPeerYeer").permitAll()
                .antMatchers("/**/chauffeurs/searchChauffeurByDisponibityByPageables/**").permitAll()
                .antMatchers("/**/chauffeurs/searchChauffeurByLocalityPageables/**").permitAll()
                .antMatchers("/**/chauffeurs/searchChauffeurByPermisPageables/**").permitAll()
                .antMatchers("/**/chauffeurs/allChauffeurs").permitAll()
                .antMatchers("/**/chauffeurs/address").permitAll()
                .antMatchers("/**/chauffeurs/chauffeurkey").permitAll()
                .antMatchers("/**/chauffeurs/chauffeurDtoSize").permitAll()
                .antMatchers("/**/chauffeurs/ctaddressIdSize").permitAll()
                .antMatchers("/**/chauffeurs/keySize").permitAll()
                .antMatchers("/**/chauffeurs/delete/{idChauffeur").permitAll()

                .antMatchers("/**/recruteurs/all").permitAll()
                .antMatchers("/**/recruteurs/create").permitAll()
                .antMatchers("/**/recruteurs/**").permitAll()
                .antMatchers("/**/recruteurs/update/*").permitAll()
                .antMatchers("/**/recruteurs/NumbersOfRecruteurs").permitAll()

                .antMatchers("/**/typeAnnonces/create").permitAll()
                .antMatchers("/**/typeAnnonces/update/{idTypeAnnonce}").permitAll()
                .antMatchers("/**/typeAnnonces/findById/{idTypeAnnonce}").permitAll()
                .antMatchers("/**/typeAnnonces/all").permitAll()
                .antMatchers("/**/typeAnnonces/searchAllTypeAnnoncesOrderByIdDesc").permitAll()
                .antMatchers("/**/typeAnnonces/delete/{idTypeAnnonce}").permitAll()

                .antMatchers("/**/annonces/create").permitAll()
                .antMatchers("/**/annonces/createAnnonceWithUser/**").permitAll()
                .antMatchers("/**/annonces/update/*").permitAll()
                .antMatchers("/**/annonces/updateStatusOfAnnonce/**").permitAll()
                .antMatchers("/**/annonces/findById/*").permitAll()
                .antMatchers("/**/annonces/findAnnonceByCustomerId/{userId}").permitAll()
                .antMatchers("/**/annonces/all").permitAll()
                .antMatchers("/**/annonces/searchAnnonceOrderByIdDesc").permitAll()
                .antMatchers("/**/annonces/searchbyReference/{reference}").permitAll()
                .antMatchers("/**/annonces/searchAnnonceByKeyword/**").permitAll()
                .antMatchers("/**/annonces/searchAnnonceByLibelle/**").permitAll()
                .antMatchers("/**/annonces/search5LatestAnnonceByIdDesc").permitAll()
                .antMatchers("/**/annonces/search6ValidateLatestAnnonceByIdDesc").permitAll()
                .antMatchers("/**/annonces/searchAnnonceByStatusPending").permitAll()
                .antMatchers("/**/annonces/searchAnnonceByStatusValide").permitAll()
                .antMatchers("/**/annonces/searchAnnonceByStatusRejet").permitAll()
                .antMatchers("/**/annonces/searchAnnonceBySelectedIsTrue").permitAll()
                .antMatchers("/**/annonces/searchAnnoncesByPermis/**").permitAll()
                .antMatchers("/**/annonces/searchAnnonceByCustomerIdOrderByIdDesc/{id}").permitAll()
                .antMatchers("/**/annonces/searchAnnonceByPageables/**").permitAll()
                .antMatchers("/**/annonces/searchAnnonceByPermisPageables/**").permitAll()
                .antMatchers("/**/annonces/NumbersOfAnnonces").permitAll()
                .antMatchers("/**/annonces/NumbersOfAnnonceByStatusPending").permitAll()
                .antMatchers("/**/annonces/NumbersOfAnnonceByStatusValidated").permitAll()
                .antMatchers("/**/annonces/NumbersOfAnnonceInMonth").permitAll()
                .antMatchers("/**/annonces/NumbersOfAnnonceInYear").permitAll()
                .antMatchers("/**/annonces/numberOfAnnonceByMonth").permitAll()
                .antMatchers("/**/annonces/numberOfAnnonceByYear").permitAll()
                .antMatchers("/**/annonces/allAnnonces").permitAll()
                .antMatchers("/**/annonces/permis").permitAll()
                .antMatchers("/**/annonces/annoncekey").permitAll()
                .antMatchers("/**/annonces/annonceSize").permitAll()
                .antMatchers("/**/annonces/ctpermisIdSize").permitAll()
                .antMatchers("/**/annonces/keySize").permitAll()
                .antMatchers("/**/annonces/delete/{idAnnonce}").permitAll()

                .antMatchers("/**/historiqueAnnonces/all").permitAll()
                .antMatchers("/**/historiqueAnnonces/searchHistoriqueAnnonceByIdDesc").permitAll()
                .antMatchers("/**/historiqueAnnonces/findById/{idHistoriqueAnnonce}").permitAll()
                .antMatchers("/**/historiqueAnnonces/create").permitAll()
                .antMatchers("/**/historiqueAnnonces/update/{idHistoriqueAnnonce}").permitAll()
                .antMatchers("/**/historiqueAnnonces/**").permitAll()
                .antMatchers("/**/historiqueAnnonces/delete/{idHistoriqueAnnonce}").permitAll()

                .antMatchers("/**/addresses/findById/*").permitAll()
                .antMatchers("/**/addresses/update/*").permitAll()
                .antMatchers("/**/addresses/all").permitAll()
                .antMatchers("/**/addresses/searchAddressOrderByIdDesc").permitAll()
                .antMatchers("/**/addresses/**").permitAll()

                .antMatchers("/**/notifications/create").permitAll()
                .antMatchers("/**/notifications/createWithChauffeur/{idNotification}").permitAll()
                .antMatchers("/**/notifications/createRatingToChauffeur/**").permitAll()
                .antMatchers("/**/notifications/findById/{idNotification}").permitAll()
                .antMatchers("/**/notifications/all").permitAll()
                .antMatchers("/**/notifications/searchAllNotificationsOrderByIdDesc").permitAll()
                .antMatchers("/**/notifications/searchTop3RatingOrderByCreatedDateDesc").permitAll()
                .antMatchers("/**/notifications/update/{idNotification}").permitAll()
                .antMatchers("/**/notifications/countNumberOfNotification").permitAll()
                .antMatchers("/**/notifications/countNumberOfNotificationByChauffeurId/{idChauff}").permitAll()
                .antMatchers("/**/notifications/searchTop4RatingOrderByCreatedDateDescByChauffeurId/{idCustomer}").permitAll()
                .antMatchers("/**/notifications/searchAllRatingByCustomerId/{idChauff}").permitAll()
                .antMatchers("/**/notifications/delete/{idNotification}").permitAll()


                .antMatchers("/**/permis/**").permitAll()
                .antMatchers("/**/permis/all").permitAll()
                .antMatchers("/**/permis/searchPermisOrderByIdDesc").permitAll()

                .antMatchers("/**/tarifs/create").permitAll()
                .antMatchers("/**/tarifs/update/{idTarif}").permitAll()
                .antMatchers("/**/tarifs/findById/{idTarif}").permitAll()
                .antMatchers("/**/tarifs/all").permitAll()
                .antMatchers("/**/tarifs/searchTarifsOrderByIdDesc").permitAll()
                .antMatchers("/**/tarifs/searchTarifsByAnnonce/{pId}").permitAll()
                .antMatchers("/**/tarifs/searchTarifByPageables/**").permitAll()
                .antMatchers("/**/tarifs/searchTarifByAnnoncePageables/**").permitAll()
                .antMatchers("/**/tarifs/delete/{idTarif}").permitAll()

                .antMatchers("/**/jetons/searchJetonsByIdDesc").permitAll()
                .antMatchers("/**/jetons/searchJetonsByCustomerId/{userId}").permitAll()
                .antMatchers("/**/jetons/findById/*").permitAll()
                .antMatchers("/**/jetons/*").permitAll()
                .antMatchers("/**/jetons/update/{idJeton}").permitAll()
                .antMatchers("/**/jetons/updateEtatOfJeton/{id}").permitAll()
                .antMatchers("/**/jetons/delete/{idJeton}").permitAll()

                .antMatchers("/**/newsleters/*").permitAll()
                .antMatchers("/**/newsleters/**").permitAll()
                .antMatchers("/**/newsleters/searchNewsleterOrderByIdDesc").permitAll()
                .antMatchers("/**/newsleters/**").permitAll()
                .antMatchers("/**/newsleters/update/{idNewsleter}").permitAll()
                .antMatchers("/**/newsleters/**").permitAll()
                .antMatchers("/**/newsleters/NumbersOfNewsleters").permitAll()
                .antMatchers("/**/newsleters/delete/{idNewsleter}").permitAll()

                .antMatchers("/**/emails/sendMailToManager").permitAll()
                .antMatchers("/**/emails/countNumberOfEmailInMonth").permitAll()
                .antMatchers("/**/emails/responseMailToCustomer").permitAll()
                .antMatchers("/**/emails/sendToRecruteur").permitAll()
                .antMatchers("/**/emails/sendToChauffeur").permitAll()
                .antMatchers("/**/emails/sendToNewsletter").permitAll()
                .antMatchers("/**/emails/findById/{idEmail}").permitAll()
                .antMatchers("/**/emails/searchAllEmailsOrderByIdDesc").permitAll()
                .antMatchers("/**/emails/countNumberOfEmailInMonth").permitAll()
                .antMatchers("/**/emails/delete/{idEmail}").permitAll()

                .antMatchers("/**/facebooks/NumberOfPagesLikes").permitAll()
                .antMatchers("/**/facebooks/NumberOfPagesFollowers").permitAll()

                .antMatchers("/**/utilisateurs/findById/{idUtilisateur}").permitAll()
                .antMatchers("/**/utilisateurs/all").permitAll()
                .antMatchers("/**/utilisateurs/searchAllUtilisateurOrderByIdDesc").permitAll()
                .antMatchers("/**/utilisateurs/update/{idUser}").permitAll()
                .antMatchers("/**/utilisateurs/avatar/{id}").permitAll()
                .antMatchers("/**/utilisateurs/uploadUserPhoto/{id}").permitAll()
                .antMatchers("/**/utilisateurs/*").permitAll()
                .antMatchers("/**/utilisateurs/updateCustomerProfileByUsername").permitAll()
                .antMatchers("/**/utilisateurs/updatePasswordByUsername").permitAll()
                .antMatchers("/**/utilisateurs/activatedUser/*").permitAll()
                .antMatchers("/**/utilisateurs/updateProfil").permitAll()
                .antMatchers("/**/utilisateurs/updateUsernameOfUserByUsername").permitAll()
                .antMatchers("/**/utilisateurs/updateUsernameOfUserById").permitAll()
                .antMatchers("/**/utilisateurs/updatePasswordByUsername").permitAll()
                .antMatchers("/**/utilisateurs/updatePasswordByUserId").permitAll()
                .antMatchers("/**/utilisateurs/NumbersOfRecruteurs").permitAll()
                .antMatchers("/**/utilisateurs/searchAllNewsRecruteursOrderByIdDesc").permitAll()

                .antMatchers("/**/historiqueLogins/all").permitAll()
                .antMatchers("/**/historiqueLogins/searchHistoriqueLoginByIdDesc").permitAll()
                .antMatchers("/**/historiqueLogins/*").permitAll()
                .antMatchers("/**/historiqueLogins/**").permitAll()
                .antMatchers("/**/historiqueLogins/delete/{idHistoriqueLogin}").permitAll()

                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                ;

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        //  .allowedOrigins("**")
                        // .allowedOrigins("https://sunuchauffeur.com")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET", "HEAD", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "TRACE")
                        .maxAge(3600L)
                        .allowedHeaders("*")
                        .exposedHeaders("Authorization")
                        .allowCredentials(true);


            }
        };
    }


    /*

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("https://sunuchauffeur.com", "http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    */


}
