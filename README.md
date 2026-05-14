# Task Description
Nucleotides that form genes in DNA molecules are represented by one of four values: A, C, G, or T. If a gene sequence is stored as a String variable, each nucleotide will require two bytes (16 bits), because Java String elements consist of Unicode characters. However, only two bits are needed to encode four possible values. For example: A can be encoded as 00, C as 01, G as 10, and T as 11. By doing this, the memory required to store the gene sequence can be reduced 8 times (from 16 bits down to 2 bits per nucleotide).

# Program Requirements
- The program must be developed as a command-line interface (CLI) application.
- The program must include user error handling for invalid inputs.
- The source code must be placed in a single file named `Main.java`.
- The `Main.java` file must begin with a comment containing the author's information (student ID number, first name, last name, and group number).
