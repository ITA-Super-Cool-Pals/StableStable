
# Current test user:
email: test@email.com

password: 123456


# Branching Strategi:
Vi bruger en simpel feature branch strategi;

Vi har main branch som er beskyttet og ikke kan blive arbejdet i direkte. Når vi skal arbejde på noget, så laver vi en ny branch til det, og når det er klart, så opretter vi et pull request til at få den ind i main.


Vi valgte specifikt ikke at kun have 1 main branch og alle abejdre på den, da det hurtigt kan blive kaotisk med merge conflicts osv.

Og vi valgte ikke har gøre det mere kompliceret med development branches, release branches osv., da vi kun er 3 i gruppen, og vi arbejder ikke direkte med en kunde der fx kunne sige "vi vil ikke have feature 3 mere", som så skulle plukkes ud.


Det betyder selvølgelig at hvis vi beslutter os for i fremtiden at vi ikke vil have login feature fx, så kan det godt være lidt halv-svært at plukke den ud, men vi så det ikke helt som et problem til vores projekt, da det er et mindre projekt med ikke så lang tid.

Man kunne lidt sige at vores "release" branch er den version der ligger der når vi opretter et release.
