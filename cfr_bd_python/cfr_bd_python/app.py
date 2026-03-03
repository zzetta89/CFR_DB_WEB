from flask import Flask, render_template, request, redirect, url_for, flash
from flask_sqlalchemy import SQLAlchemy
from sqlalchemy.exc import IntegrityError

app = Flask(__name__)

# CONFIGURARE BAZA DE DATE

app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://username:parola@localhost/nume_baza_de_date'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
app.config['SECRET_KEY'] = '0000'

db = SQLAlchemy(app)

# MODELE

class Sucursale(db.Model):
    __tablename__ = 'sucursale'
    id_sucursala = db.Column(db.BigInteger, primary_key=True, autoincrement=True)
    adresa = db.Column(db.String(255))
    denumire = db.Column(db.String(255), nullable=False)
    oras = db.Column(db.String(255), nullable=False)
    bilete = db.relationship('Bilet', backref='sucursala_rel', lazy=True, cascade="all, delete")

class Calatori(db.Model):
    __tablename__ = 'calatori'
    id_calator = db.Column(db.BigInteger, primary_key=True, autoincrement=True)
    cnp = db.Column(db.String(255), unique=True)
    data_nasterii = db.Column(db.String(255))
    email = db.Column(db.String(255), nullable=False, unique=True)
    nume = db.Column(db.String(255), nullable=False)
    prenume = db.Column(db.String(255), nullable=False)
    bilete = db.relationship('Bilet', backref='calator_rel', lazy=True, cascade="all, delete")

class Bilet(db.Model):
    __tablename__ = 'bilet'
    id_bilet = db.Column(db.BigInteger, primary_key=True, autoincrement=True)
    data_calatoriei = db.Column(db.String(255))
    destinatie = db.Column(db.String(255), nullable=False)
    nr_loc = db.Column(db.Integer, nullable=False)
    nr_tren = db.Column(db.String(255), nullable=False)
    nr_vagon = db.Column(db.Integer, nullable=False)
    plecare = db.Column(db.String(255), nullable=False)
    id_calator = db.Column(db.BigInteger, db.ForeignKey('calatori.id_calator', ondelete='CASCADE', onupdate='CASCADE'))
    id_sucursala = db.Column(db.BigInteger, db.ForeignKey('sucursale.id_sucursala', ondelete='CASCADE', onupdate='CASCADE'))

# RUTE PRINCIPALE

@app.route('/')
def index():
    return render_template('index.html')

# CĂLĂTORI
@app.route('/calatori')
def list_calatori():
    calatori = Calatori.query.all()
    return render_template('calatori.html', calatori=calatori)


@app.route('/calatori/adauga', methods=['GET', 'POST'])
def adauga_calator():
    if request.method == 'POST':
        cnp = request.form['cnp']

        # VALIDARE CNP (Backend)
        if len(cnp) != 13 or not cnp.isdigit():
            flash('Eroare: CNP-ul trebuie să conțină exact 13 cifre!', 'danger')
            return render_template('form_calator.html', calator=None)  # Rămâne în pagină

        nou = Calatori(
            nume=request.form['nume'], prenume=request.form['prenume'],
            email=request.form['email'], cnp=cnp,
            data_nasterii=request.form['data_nasterii']
        )
        try:
            db.session.add(nou)
            db.session.commit()
            flash('Călător adăugat!', 'success')
            return redirect(url_for('list_calatori'))
        except IntegrityError:
            db.session.rollback()
            flash('Eroare: Email sau CNP duplicat!', 'danger')

    return render_template('form_calator.html', calator=None)


@app.route('/calatori/editeaza/<int:id>', methods=['GET', 'POST'])
def editeaza_calator(id):
    calator = Calatori.query.get_or_404(id)
    if request.method == 'POST':
        cnp = request.form['cnp']

        # VALIDARE CNP (Backend)
        if len(cnp) != 13 or not cnp.isdigit():
            flash('Eroare: CNP-ul trebuie să conțină exact 13 cifre!', 'danger')
            return render_template('form_calator.html', calator=calator)

        calator.nume = request.form['nume']
        calator.prenume = request.form['prenume']
        calator.email = request.form['email']
        calator.cnp = cnp
        calator.data_nasterii = request.form['data_nasterii']
        try:
            db.session.commit()
            flash('Călător actualizat!', 'success')
            return redirect(url_for('list_calatori'))
        except IntegrityError:
            db.session.rollback()
            flash('Eroare: Datele intră în conflict cu un alt cont existent.', 'danger')

    return render_template('form_calator.html', calator=calator)

@app.route('/calatori/sterge/<int:id>')
def sterge_calator(id):
    c = Calatori.query.get_or_404(id)
    db.session.delete(c)
    db.session.commit()
    flash('Călător șters!', 'info')
    return redirect(url_for('list_calatori'))

# SUCURSALE
@app.route('/sucursale')
def list_sucursale():
    sucursale = Sucursale.query.all()
    return render_template('sucursale.html', sucursale=sucursale)

@app.route('/sucursale/adauga', methods=['GET', 'POST'])
def adauga_sucursala():
    if request.method == 'POST':
        nou = Sucursale(
            denumire=request.form['denumire'],
            oras=request.form['oras'],
            adresa=request.form['adresa']
        )
        try:
            db.session.add(nou)
            db.session.commit()
            flash('Sucursală creată cu succes!', 'success')
            return redirect(url_for('list_sucursale'))
        except IntegrityError:
            db.session.rollback()
            flash(f"Eroare: Există deja o sucursală '{request.form['denumire']}' în orașul '{request.form['oras']}'!", 'danger')
    return render_template('form_sucursala.html', sucursala=None)

@app.route('/sucursale/editeaza/<int:id>', methods=['GET', 'POST'])
def editeaza_sucursala(id):
    sucursala = Sucursale.query.get_or_404(id)
    if request.method == 'POST':
        sucursala.denumire = request.form['denumire']
        sucursala.oras = request.form['oras']
        sucursala.adresa = request.form['adresa']
        try:
            db.session.commit()
            flash('Sucursală actualizată!', 'success')
            return redirect(url_for('list_sucursale'))
        except IntegrityError:
            db.session.rollback()
            flash('Eroare: Combinația Denumire + Oraș există deja!', 'danger')
    return render_template('form_sucursala.html', sucursala=sucursala)

@app.route('/sucursale/sterge/<int:id>')
def sterge_sucursala(id):
    s = Sucursale.query.get_or_404(id)
    db.session.delete(s)
    db.session.commit()
    flash('Sucursală ștearsă!', 'info')
    return redirect(url_for('list_sucursale'))

# LOGICA BILETE
@app.route('/bilete')
def list_bilete():
    bilete = Bilet.query.all()
    return render_template('bilete.html', bilete=bilete)

@app.route('/bilete/adauga', methods=['GET', 'POST'])
def adauga_bilet():
    if request.method == 'POST':
        nou = Bilet(
            nr_tren=request.form['nr_tren'], nr_vagon=request.form['nr_vagon'],
            nr_loc=request.form['nr_loc'], data_calatoriei=request.form['data_calatoriei'],
            destinatie=request.form['destinatie'], plecare=request.form['plecare'],
            id_calator=request.form['id_calator'], id_sucursala=request.form['id_sucursala']
        )
        try:
            db.session.add(nou)
            db.session.commit()
            flash('Bilet emis!', 'success')
            return redirect(url_for('list_bilete'))
        except IntegrityError:
            db.session.rollback()
            flash('Eroare: Locul este deja rezervat în această zi!', 'danger')
    calatori = Calatori.query.all()
    sucursale = Sucursale.query.all()
    return render_template('form_bilet.html', bilet=None, calatori=calatori, sucursale=sucursale)

@app.route('/bilete/editeaza/<int:id>', methods=['GET', 'POST'])
def editeaza_bilet(id):
    bilet = Bilet.query.get_or_404(id)

    if request.method == 'POST':
        bilet.nr_tren = request.form['nr_tren']
        bilet.nr_vagon = request.form['nr_vagon']
        bilet.nr_loc = request.form['nr_loc']
        bilet.data_calatoriei = request.form['data_calatoriei']
        bilet.destinatie = request.form['destinatie']
        bilet.plecare = request.form['plecare']
        bilet.id_calator = request.form['id_calator']
        bilet.id_sucursala = request.form['id_sucursala']

        try:
            db.session.commit()
            flash('Bilet modificat cu succes!', 'success')
            return redirect(url_for('list_bilete'))
        except IntegrityError:
            db.session.rollback()
            flash('Eroare: Modificarea creează un conflict (loc deja ocupat)!', 'danger')

    # Trebuie să trimitem listele din nou pentru dropdown-uri
    calatori = Calatori.query.all()
    sucursale = Sucursale.query.all()
    return render_template('form_bilet.html', bilet=bilet, calatori=calatori, sucursale=sucursale)

@app.route('/bilete/sterge/<int:id>')
def sterge_bilet(id):
    b = Bilet.query.get_or_404(id)
    db.session.delete(b)
    db.session.commit()
    flash('Bilet anulat!', 'info')
    return redirect(url_for('list_bilete'))

if __name__ == '__main__':
    app.run(debug=True)