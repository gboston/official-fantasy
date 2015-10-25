package demo;

import demo.domain.players.Player;
import demo.domain.players.TeamPlayerRepository;
import demo.domain.users.User;
import demo.domain.users.UserRepository;
import demo.service.FileProcessingService;
import org.h2.tools.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

    @Bean
    CommandLineRunner getCommandLineRunner(UserRepository repo,TeamPlayerRepository playerRepo,FileProcessingService fileProcessingService){
        return strings -> {
            User glenn = new User("Glenn","bostoen",25,"picture");
            User wouter = new User("Wouter","baert",24,"picture");
            User galle = new User("Galle","Galle",25,"picture");
            User dendooven = new User("Dendooven","Dendooven",25,"picture");
            User stan = new User("Stan","Vanhecke",25,"picture");
            repo.save(glenn);
            repo.save(wouter);
            repo.save(galle);
            repo.save(dendooven);
            repo.save(stan);

            List<String> playerNamesGlenn = Arrays.asList("Cech,Alba,Varane,Smolnikov,Smalling,Kroos,De Bruyne,Koke,Sterling,Suarez,Lewandowski,Pepe,Shatov,Pedro,De gea".split(","));
            List<String> playerNamesWouter  = Arrays.asList("T Stegen,Alaba,Marcelo,Luisao,Ivanovic,Toure,Valbuena,Iniesta,Di Maria,Ronaldo,Sanchez,Griezman,Fuego,Aboubakar,Casillas".split(","));
            List<String> playerNamesGalle = Arrays.asList("Neuer,Mascherano,F Luis,T Silva,Azpilicueta,Rakitic,Matuidi,Ozil,Douglas Costa,Messi,Martinez,Brahimi,Cavani,Memphis,Lodygin".split(","));
            List<String> playerNamesStan = Arrays.asList("Oblak,Boateng,Terry,Mustafi,Maicon,Vidal,Pogba,James,Feghouli,Ibrahimovic,Neymar,Nelsinho,Fekir,Hazard,Sirigu".split(","));
            List<String> playerNamesDendooven = Arrays.asList("Navas,Ramos,Alves,Godin,Juanfran,Robben,Fabregas,Mata,Gotze,Muller,Benzema,Pique,Bale,Aguero,Buffon".split(","));

            playerNamesGlenn.stream().forEach(x -> glenn.addPlayer(new Player(x)));
            repo.save(glenn);

            playerNamesGalle.stream().forEach(x -> galle.addPlayer(new Player(x)));
            repo.save(galle);

            playerNamesWouter.stream().forEach(x -> wouter.addPlayer(new Player(x)));
            repo.save(wouter);

            playerNamesStan.stream().forEach(x -> stan.addPlayer(new Player(x)));
            repo.save(stan);

            playerNamesDendooven.stream().forEach(x -> dendooven.addPlayer(new Player(x)));
            repo.save(dendooven);

            //playerNamesGalle.stream().forEach(x -> galle.addPlayer(new Player(x)));
            //repo.save(galle);

            //playerRepo.findAll().forEach(System.out::println);

            fileProcessingService.calculatePointsPlayers();
            //fileProcessingService.calculateActiveMatchDays();
            repo.findAll().stream().forEach(x-> {
                int totalPoints = x.getTeamPlayers().stream().flatMap(p ->p.getActiveMatchDayPoints().stream()).mapToInt(playDay->playDay.getPoints()).sum();
                System.out.println(x.getName()+" :"+totalPoints);
            });
        };
    }
    @Bean(initMethod = "start",destroyMethod = "stop")
    public Server getServer() throws SQLException {
        return Server.createTcpServer("-tcp","-tcpAllowOthers","-tcpPort","8081");
    }

    @Bean(initMethod = "start",destroyMethod = "stop")
    Server getH2WebServer() throws SQLException {
        return Server.createWebServer("-web","-webAllowOthers","-webPort","8083");
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
