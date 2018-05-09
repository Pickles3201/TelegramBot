import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import java.util.ArrayList;
import java.util.Random;

public class SnakerHomesBot extends TelegramLongPollingBot
{
    ArrayList<String> people = new ArrayList<String>();
    ArrayList<String> snaking = new ArrayList<String>();
    ArrayList<String> snaked = new ArrayList<String>();
    ArrayList<String> homes = new ArrayList<String>();
    ArrayList<String> homesing = new ArrayList<String>();

    public void onUpdateReceived(Update update)
    {
        String text = update.getMessage().getText().toLowerCase();
        SendMessage message = new SendMessage();

        Random rand = new Random();
        int num = rand.nextInt(16);
        if(num == 6 || num == 9)
        {
            if(num == 6)
            {
                message.setText("Ooh wee");
            }
            if(num == 9)
            {
                message.setText("Heah");
            }
        }

        if(text.substring(text.indexOf(" ") + 1).equalsIgnoreCase("me daddy"))
        {
            message.setText("* Snaker Homes " + text.substring(0, text.indexOf(" ")) + "s " + update.getMessage().getFrom().getFirstName() + " *");
        }

        if(text.indexOf("homes") != -1)
        {
            message.setText("Hooooomes");
        }

        if(text.indexOf("snake") != -1)
        {
            message.setText("Snaaaaake");
        }

        String fullName = update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName();
        if(people.indexOf(fullName) == -1)
        {
            people.add(fullName);
            snaking.add("no one");
            snaked.add("no one");
            homes.add("no one");
            homesing.add("no one");
        }

        if(text.indexOf("/rape") != -1)
        {
            message.setText(update.getMessage().getFrom().getFirstName() + " has been raped by Rohan P.");
        }

        if(text.equalsIgnoreCase("/molest"))
        {
            message.setText(update.getMessage().getFrom().getFirstName() + " has been molested by Rohan P.");
        }

        if(text.indexOf("/snake") != -1)
        {
            try
            {
                String snakerName = update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName();
                String beingSnaked = update.getMessage().getReplyToMessage().getFrom().getFirstName() + " " + update.getMessage().getReplyToMessage().getFrom().getLastName();
                int snaker = people.indexOf(snakerName);
                int snakered = people.indexOf(beingSnaked);
                System.out.println(update.getMessage().getReplyToMessage().getFrom().getFirstName() + " " + update.getMessage().getReplyToMessage().getFrom().getLastName());
                if(update.getMessage().getReplyToMessage().getFrom().getFirstName().equalsIgnoreCase("Snaker Homes"))
                {
                    message.setText("You cannot snake a divine being.");
                    System.out.println(update.getMessage().getReplyToMessage().getFrom().getFirstName());
                }
                else
                {
                    message.setText(snakerName + " unsnaked " + snaking.get(snaker) + " and started snaking " + beingSnaked);
                }
                snaking.set(snaker, beingSnaked);
                snaked.set(snakered, snakerName);
            }
            catch(NullPointerException e)
            {
                message.setText("Reply to the user you want to snake, you snake.");
            }
        }

        if(text.equalsIgnoreCase("/homes"))
        {
            try
            {
                String homesName = update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName();
                String beingHomes = update.getMessage().getReplyToMessage().getFrom().getFirstName() + " " + update.getMessage().getReplyToMessage().getFrom().getLastName();
                int homeser = people.indexOf(homesName);
                int homesered = people.indexOf(beingHomes);
                if(update.getMessage().getReplyToMessage().getFrom().getFirstName().equalsIgnoreCase("Snaker Homes"))
                {
                    message.setText("You cannot homes a divine being.");
                }
                else
                {
                    message.setText(homesName + " unhomed " + homesing.get(homeser) + " and made " + beingHomes + " his homes.");
                }
                homesing.set(homeser, beingHomes);
                homes.set(homesered, homesName);
            }
            catch(NullPointerException e)
            {
                message.setText("Reply to the user you want to be your homes, homes.");
            }
        }

        if(text.indexOf("/whatsmysnake") != -1)
        {
            String name = update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName();
            int snakeIndex = people.indexOf(name);
            message.setText(name + " is currently snaking " + snaking.get(snakeIndex) + " and his current homes is " + homes.get(snakeIndex) + ".");
        }

        if(text.equalsIgnoreCase("Snake Me") || text.equalsIgnoreCase("Snake Me Daddy"))
        {
            message.setText("You cannot snake a homes.");
        }

        if(text.equalsIgnoreCase("Homes Me") || text.equalsIgnoreCase("Homes Me Daddy"))
        {
            message.setText("You cannot homes a snake.");
        }

        message.setChatId(update.getMessage().getChatId());
        try
        {
            execute(message);
        }
        catch (TelegramApiException e)
        {
            e.printStackTrace();
        }
    }

    public String getBotUsername()
    {
        return "Snaker Homes";
    }

    public String getBotToken()
    {
        return "451008907:AAEKzh-q48fFLQF4hqhPFYV3tR4Tgnk6kuw";
    }
}