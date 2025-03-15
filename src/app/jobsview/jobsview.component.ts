import { CommonModule, NgClass } from '@angular/common';
import { Component, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';

interface Job {
  title: string;
  company: string;
  location: string;
  jobType: string;
  salary: string;
  remoteOptions: string;
  experienceLevel: string;
  educationLevel: string;
  description: string;
  responsibilities: string[];
  qualifications: string[];
  skills: string[];
  benefits: string[];
  applicationDeadline: string;
  datePosted: string;
}
@Component({
  selector: 'app-jobsview',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './jobsview.component.html',
  styleUrl: './jobsview.component.css',
})
export class JobsviewComponent {
  jobs: Job[] = [
    {
      title: 'Senior React Developer',
      company: 'Tech Solutions Inc.',
      location: 'San Francisco, CA',
      jobType: 'Full-time',
      salary: '$100,000 - $120,000 per year',
      remoteOptions: 'Fully Remote',
      experienceLevel: 'Mid-Senior Level',
      educationLevel: "Bachelor's Degree in Computer Science or related field",
      description:
        'Join our dynamic team of developers working on cutting-edge React applications...',
      responsibilities: [
        'Develop new user-facing features using React.js',
        'Build reusable components and front-end libraries for future use',
        'Translate designs and wireframes into high-quality code',
        'Optimize components for maximum performance across a vast array of web-capable devices and browsers',
      ],
      qualifications: [
        'Strong proficiency in JavaScript, including DOM manipulation and the JavaScript object model',
        'Thorough understanding of React.js and its core principles',
        'Experience with popular React.js workflows (such as Flux or Redux)',
        'Familiarity with newer specifications of EcmaScript',
        'Experience with data structure libraries (e.g., Immutable.js)',
        'Knowledge of modern authorization mechanisms, such as JSON Web Token',
        'Familiarity with modern front-end build pipelines and tools',
        'Experience with common front-end development tools such as Babel, Webpack, NPM, etc.',
        'A knack for benchmarking and optimization',
        'Familiarity with code versioning tools such as Git, SVN, etc.',
      ],
      skills: ['React', 'Redux', 'JavaScript', 'HTML', 'CSS'],
      benefits: ['Health insurance', 'Retirement plan', 'Flexible work hours'],
      applicationDeadline: 'July 15, 2024',
      datePosted: 'June 10, 2024',
    },
    {
      title: 'Data Scientist',
      company: 'AI Innovations Ltd.',
      location: 'New York, NY',
      jobType: 'Full-time',
      salary: '$110,000 - $140,000 per year',
      remoteOptions: 'Hybrid',
      experienceLevel: 'Mid-Senior Level',
      educationLevel: "Master's Degree in Data Science or related field",
      description: 'Work with large datasets to find patterns and insights...',
      responsibilities: [
        'Analyze large amounts of information to discover trends and patterns',
        'Build predictive models and machine-learning algorithms',
        'Combine models through ensemble modeling',
        'Present information using data visualization techniques',
      ],
      qualifications: [
        'Proven experience as a Data Scientist',
        'Experience in data mining and data analysis',
        'Understanding of machine-learning and operations research',
        'Knowledge of R, SQL, and Python; familiarity with Scala, Java or C++ is an asset',
      ],
      skills: ['Python', 'R', 'Machine Learning', 'Data Analysis', 'SQL'],
      benefits: ['Health insurance', 'Stock options', 'Gym membership'],
      applicationDeadline: 'August 1, 2024',
      datePosted: 'June 20, 2024',
    },
    {
      title: 'Project Manager',
      company: 'Global Corp.',
      location: 'Remote',
      jobType: 'Contract',
      salary: '$80,000 - $100,000 per year',
      remoteOptions: 'Fully Remote',
      experienceLevel: 'Senior Level',
      educationLevel:
        "Bachelor's Degree in Business Management or related field",
      description:
        'Oversee project operations and ensure that projects are delivered on time...',
      responsibilities: [
        'Coordinate internal resources and third parties/vendors for project execution',
        'Ensure that all projects are delivered on time, within scope and within budget',
        'Develop project scopes and objectives, involving all relevant stakeholders',
        'Manage relationships with clients and stakeholders',
      ],
      qualifications: [
        'Proven working experience as a project manager',
        'Excellent client-facing and internal communication skills',
        'Solid organizational skills including attention to detail and multitasking skills',
        'PMP / PRINCE II certification is a plus',
      ],
      skills: [
        'Project Management',
        'Leadership',
        'Risk Management',
        'Budgeting',
      ],
      benefits: ['Remote work', 'Professional development', 'Flexible hours'],
      applicationDeadline: 'September 1, 2024',
      datePosted: 'July 5, 2024',
    },
    {
      title: 'Software Engineer',
      company: 'Innovative Solutions',
      location: 'Austin, TX',
      jobType: 'Full-time',
      salary: '$90,000 - $110,000 per year',
      remoteOptions: 'Partial Remote',
      experienceLevel: 'Entry-Level',
      educationLevel: "Bachelor's Degree in Computer Science or related field",
      description: 'Develop and maintain software applications...',
      responsibilities: [
        'Write clean, scalable code using .NET programming languages',
        'Test and deploy applications and systems',
        'Revise, update, refactor and debug code',
        'Improve existing software',
      ],
      qualifications: [
        'Experience with software design and development in a test-driven environment',
        'Knowledge of coding languages (e.g., C#, .NET, Java)',
        'Familiarity with Agile development methodologies',
        'Excellent problem-solving and communication skills',
      ],
      skills: ['C#', '.NET', 'Java', 'SQL', 'Problem Solving'],
      benefits: ['Health insurance', '401(k) plan', 'Paid time off'],
      applicationDeadline: 'August 15, 2024',
      datePosted: 'June 30, 2024',
    },
    {
      title: 'UI/UX Designer',
      company: 'Creative Minds',
      location: 'Chicago, IL',
      jobType: 'Full-time',
      salary: '$70,000 - $90,000 per year',
      remoteOptions: 'Hybrid',
      experienceLevel: 'Mid-Level',
      educationLevel: "Bachelor's Degree in Design or related field",
      description:
        'Design user interfaces and create engaging user experiences...',
      responsibilities: [
        'Create wireframes, storyboards, user flows, process flows and site maps',
        'Design the UI architecture, interface, and interaction flow of applications and experiences',
        'Collaborate with product management and engineering to define and implement innovative solutions',
        'Establish and promote design guidelines, best practices, and standards',
      ],
      qualifications: [
        'Proven UI/UX design experience',
        'Proficiency in design tools like Sketch, Adobe XD, and Figma',
        'Experience in designing for mobile and web platforms',
        'A strong portfolio demonstrating user-centered design thinking',
      ],
      skills: ['UI Design', 'UX Design', 'Sketch', 'Figma', 'Adobe XD'],
      benefits: ['Health insurance', 'Remote work', 'Creative environment'],
      applicationDeadline: 'September 10, 2024',
      datePosted: 'July 15, 2024',
    },
    {
      title: 'Marketing Specialist',
      company: 'Brand Builders',
      location: 'Los Angeles, CA',
      jobType: 'Part-time',
      salary: '$50,000 - $65,000 per year',
      remoteOptions: 'Partial Remote',
      experienceLevel: 'Mid-Level',
      educationLevel: "Bachelor's Degree in Marketing or related field",
      description: 'Plan and execute marketing campaigns...',
      responsibilities: [
        'Conduct market research to find answers about consumer requirements, habits, and trends',
        'Brainstorm and develop ideas for creative marketing campaigns',
        'Assist in outbound or inbound marketing activities by demonstrating expertise in various areas',
        'Collaborate with marketing and other professionals to coordinate brand awareness and marketing efforts',
      ],
      qualifications: [
        'Proven experience as a marketing specialist',
        'Thorough understanding of marketing elements (including traditional and digital marketing)',
        'Experience in market research and data analysis',
        'Knowledge of HTML, CSS, and web development tools',
      ],
      skills: ['Marketing Strategy', 'SEO', 'Content Creation', 'Analytics'],
      benefits: ['Health insurance', 'Flexible hours', 'Professional growth'],
      applicationDeadline: 'October 1, 2024',
      datePosted: 'July 25, 2024',
    },
    {
      title: 'Full Stack Developer',
      company: 'Tech Innovators',
      location: 'Boston, MA',
      jobType: 'Full-time',
      salary: '$95,000 - $130,000 per year',
      remoteOptions: 'Fully Remote',
      experienceLevel: 'Mid-Senior Level',
      educationLevel: "Bachelor's Degree in Computer Science or related field",
      description: 'Develop front-end and back-end web applications...',
      responsibilities: [
        'Work with development teams and product managers to ideate software solutions',
        'Design client-side and server-side architecture',
        'Develop and manage well-functioning databases and applications',
        'Write effective APIs',
      ],
      qualifications: [
        'Experience developing desktop and mobile applications',
        'Familiarity with common stacks',
        'Knowledge of multiple front-end languages and libraries (e.g., HTML/ CSS, JavaScript, XML, jQuery)',
        'Knowledge of multiple back-end languages (e.g., C#, Java, Python) and JavaScript frameworks (e.g., Angular, React, Node.js)',
      ],
      skills: ['JavaScript', 'Node.js', 'React', 'HTML/CSS', 'API Development'],
      benefits: ['Health insurance', 'Retirement plan', 'Remote work'],
      applicationDeadline: 'October 15, 2024',
      datePosted: 'August 1, 2024',
    },
    {
      title: 'Cloud Engineer',
      company: 'SkyNet Systems',
      location: 'Seattle, WA',
      jobType: 'Full-time',
      salary: '$110,000 - $150,000 per year',
      remoteOptions: 'Hybrid',
      experienceLevel: 'Senior Level',
      educationLevel: "Bachelor's Degree in Computer Science or related field",
      description: 'Design and manage cloud environments...',
      responsibilities: [
        'Design and implement cloud solutions for the organization',
        'Manage cloud environments in accordance with company security guidelines',
        'Collaborate with engineering teams to build and optimize cloud services',
        'Monitor cloud infrastructure performance and availability',
      ],
      qualifications: [
        'Proven experience as a Cloud Engineer or similar role',
        'Experience with AWS, Azure, or Google Cloud',
        'Understanding of cloud security best practices',
        'Knowledge of cloud architecture and governance',
      ],
      skills: ['AWS', 'Azure', 'Google Cloud', 'Cloud Security', 'DevOps'],
      benefits: ['Health insurance', '401(k)', 'Stock options'],
      applicationDeadline: 'November 1, 2024',
      datePosted: 'August 15, 2024',
    },
    {
      title: 'Cybersecurity Analyst',
      company: 'SecureTech',
      location: 'Washington, D.C.',
      jobType: 'Full-time',
      salary: '$100,000 - $130,000 per year',
      remoteOptions: 'Hybrid',
      experienceLevel: 'Mid-Senior Level',
      educationLevel:
        "Bachelor's Degree in Information Security or related field",
      description: 'Protect company assets from cyber threats...',
      responsibilities: [
        'Monitor security access and report suspicious activity',
        'Install and configure security software to prevent attacks',
        'Document and define corporate security policies',
        'Train staff on data security practices',
      ],
      qualifications: [
        'Experience with security information and event management (SIEM)',
        'Proficient in penetration testing, vulnerability scanning, and firewalls',
        'Knowledge of compliance standards like ISO 27001, NIST, and GDPR',
        'Experience with incident response and forensics',
      ],
      skills: [
        'Cybersecurity',
        'SIEM',
        'Penetration Testing',
        'Compliance',
        'Incident Response',
      ],
      benefits: ['Health insurance', '401(k)', 'Gym membership'],
      applicationDeadline: 'December 1, 2024',
      datePosted: 'August 25, 2024',
    },
    {
      title: 'DevOps Engineer',
      company: 'NextGen Solutions',
      location: 'Denver, CO',
      jobType: 'Full-time',
      salary: '$105,000 - $135,000 per year',
      remoteOptions: 'Partial Remote',
      experienceLevel: 'Senior Level',
      educationLevel: "Bachelor's Degree in Computer Science or related field",
      description: 'Automate and streamline development operations...',
      responsibilities: [
        'Build and maintain CI/CD pipelines',
        'Automate infrastructure provisioning and deployment',
        'Collaborate with development teams to improve deployment speed and quality',
        'Monitor system performance and reliability',
      ],
      qualifications: [
        'Experience with containerization and orchestration (Docker, Kubernetes)',
        'Strong understanding of cloud services and infrastructure',
        'Proficiency in scripting languages (Bash, Python, etc.)',
        'Knowledge of configuration management tools (e.g., Ansible, Terraform)',
      ],
      skills: ['CI/CD', 'Docker', 'Kubernetes', 'Python', 'AWS'],
      benefits: ['Health insurance', 'Remote work', 'Retirement plan'],
      applicationDeadline: 'December 15, 2024',
      datePosted: 'September 5, 2024',
    },
    {
      title: 'Technical Support Engineer',
      company: 'Global Tech Support',
      location: 'Dallas, TX',
      jobType: 'Full-time',
      salary: '$50,000 - $70,000 per year',
      remoteOptions: 'On-site',
      experienceLevel: 'Entry-Level',
      educationLevel:
        "Associate's Degree in Information Technology or related field",
      description: 'Provide technical support to clients and customers...',
      responsibilities: [
        'Respond to technical support tickets',
        'Identify and troubleshoot technical issues',
        'Guide customers through problem-solving processes',
        'Collaborate with development teams for issue resolution',
      ],
      qualifications: [
        'Excellent problem-solving skills',
        'Strong communication skills',
        'Basic understanding of networking and hardware',
        'Experience with ticketing systems (e.g., Zendesk, Jira)',
      ],
      skills: [
        'Technical Support',
        'Problem Solving',
        'Customer Service',
        'Networking',
      ],
      benefits: ['Health insurance', 'Paid time off', 'Training programs'],
      applicationDeadline: 'January 15, 2025',
      datePosted: 'September 20, 2024',
    },
    {
      title: 'Quality Assurance Tester',
      company: 'Software Solutions Inc.',
      location: 'Raleigh, NC',
      jobType: 'Contract',
      salary: '$60,000 - $75,000 per year',
      remoteOptions: 'Hybrid',
      experienceLevel: 'Mid-Level',
      educationLevel: "Bachelor's Degree in Computer Science or related field",
      description: 'Test and validate software products...',
      responsibilities: [
        'Design test plans, scenarios, scripts, and procedures',
        'Execute tests and analyze results',
        'Identify software defects and track them in bug tracking systems',
        'Collaborate with development teams to resolve issues',
      ],
      qualifications: [
        'Experience in software testing or quality assurance',
        'Familiarity with Agile development processes',
        'Experience with automated testing tools (e.g., Selenium)',
        'Strong attention to detail',
      ],
      skills: ['Software Testing', 'QA', 'Automated Testing', 'Selenium'],
      benefits: ['Health insurance', 'Remote work', 'Contractor perks'],
      applicationDeadline: 'February 1, 2025',
      datePosted: 'October 5, 2024',
    },
    {
      title: 'Database Administrator',
      company: 'DataCorp',
      location: 'Miami, FL',
      jobType: 'Full-time',
      salary: '$85,000 - $110,000 per year',
      remoteOptions: 'Partial Remote',
      experienceLevel: 'Mid-Senior Level',
      educationLevel:
        "Bachelor's Degree in Information Technology or related field",
      description: 'Manage and maintain company databases...',
      responsibilities: [
        'Install, configure, and upgrade database management systems',
        'Ensure database security, availability, and performance',
        'Develop and implement backup and recovery plans',
        'Monitor database performance and optimize queries',
      ],
      qualifications: [
        'Proven experience as a Database Administrator',
        'Familiarity with database design, coding, and documentation',
        'Knowledge of SQL and database management systems (e.g., MySQL, PostgreSQL, Oracle)',
        'Experience with database performance tuning and optimization',
      ],
      skills: [
        'SQL',
        'Database Management',
        'Performance Tuning',
        'Backup and Recovery',
      ],
      benefits: ['Health insurance', '401(k)', 'Remote work options'],
      applicationDeadline: 'March 1, 2025',
      datePosted: 'October 15, 2024',
    },
    {
      title: 'HR Specialist',
      company: 'People First Solutions',
      location: 'Atlanta, GA',
      jobType: 'Full-time',
      salary: '$55,000 - $70,000 per year',
      remoteOptions: 'Hybrid',
      experienceLevel: 'Entry-Level',
      educationLevel: "Bachelor's Degree in Human Resources or related field",
      description: 'Support HR operations and employee engagement...',
      responsibilities: [
        'Assist with recruitment and onboarding processes',
        'Manage employee records and ensure compliance with labor laws',
        'Support performance management and employee development programs',
        'Coordinate employee benefits and payroll processes',
      ],
      qualifications: [
        'Experience in HR or related field',
        'Strong communication and interpersonal skills',
        'Knowledge of HR software (e.g., Workday, ADP)',
        'Familiarity with labor laws and regulations',
      ],
      skills: [
        'HR Management',
        'Recruitment',
        'Onboarding',
        'Employee Relations',
      ],
      benefits: ['Health insurance', 'Retirement plan', 'Hybrid work options'],
      applicationDeadline: 'March 15, 2025',
      datePosted: 'October 25, 2024',
    },
    {
      title: 'Product Manager',
      company: 'InnovateTech',
      location: 'San Diego, CA',
      jobType: 'Full-time',
      salary: '$110,000 - $140,000 per year',
      remoteOptions: 'Partial Remote',
      experienceLevel: 'Senior Level',
      educationLevel: "Bachelor's Degree in Business or related field",
      description: 'Lead product development and strategy...',
      responsibilities: [
        'Define the product vision and strategy',
        'Develop product roadmaps and prioritize features',
        'Collaborate with cross-functional teams to deliver product solutions',
        'Conduct market research and analyze customer feedback',
      ],
      qualifications: [
        'Proven experience as a Product Manager',
        'Strong understanding of product lifecycle management',
        'Experience with Agile methodologies and tools',
        'Excellent communication and leadership skills',
      ],
      skills: ['Product Management', 'Agile', 'Leadership', 'Market Research'],
      benefits: ['Health insurance', 'Stock options', 'Remote work'],
      applicationDeadline: 'April 1, 2025',
      datePosted: 'November 5, 2024',
    },
    {
      title: 'Content Writer',
      company: 'Creative Content Co.',
      location: 'Portland, OR',
      jobType: 'Part-time',
      salary: '$45,000 - $60,000 per year',
      remoteOptions: 'Fully Remote',
      experienceLevel: 'Mid-Level',
      educationLevel:
        "Bachelor's Degree in English, Journalism, or related field",
      description: 'Write engaging and SEO-friendly content for websites...',
      responsibilities: [
        'Research industry-related topics and keywords',
        'Write clear, concise, and compelling content',
        'Optimize content for search engines',
        'Collaborate with designers to enhance the content with visuals',
      ],
      qualifications: [
        'Proven experience as a content writer or similar role',
        'Excellent writing and editing skills',
        'Knowledge of SEO best practices',
        'Familiarity with content management systems (e.g., WordPress)',
      ],
      skills: ['Content Writing', 'SEO', 'Copywriting', 'Research'],
      benefits: ['Remote work', 'Flexible hours', 'Professional growth'],
      applicationDeadline: 'April 15, 2025',
      datePosted: 'November 15, 2024',
    },
    {
      title: 'Senior React Developer',
      company: 'Tech Solutions Inc.',
      location: 'San Francisco, CA',
      jobType: 'Full-time',
      salary: '$100,000 - $120,000 per year',
      remoteOptions: 'Fully Remote',
      experienceLevel: 'Mid-Senior Level',
      educationLevel: "Bachelor's Degree in Computer Science or related field",
      description:
        'Join our dynamic team of developers working on cutting-edge React applications...',
      responsibilities: [
        'Develop new user-facing features using React.js',
        'Build reusable components and front-end libraries for future use',
        'Translate designs and wireframes into high-quality code',
        'Optimize components for maximum performance across a vast array of web-capable devices and browsers',
      ],
      qualifications: [
        'Strong proficiency in JavaScript, including DOM manipulation and the JavaScript object model',
        'Thorough understanding of React.js and its core principles',
        'Experience with popular React.js workflows (such as Flux or Redux)',
        'Familiarity with newer specifications of EcmaScript',
        'Experience with data structure libraries (e.g., Immutable.js)',
        'Knowledge of modern authorization mechanisms, such as JSON Web Token',
        'Familiarity with modern front-end build pipelines and tools',
        'Experience with common front-end development tools such as Babel, Webpack, NPM, etc.',
        'A knack for benchmarking and optimization',
        'Familiarity with code versioning tools such as Git, SVN, etc.',
      ],
      skills: ['React', 'Redux', 'JavaScript', 'HTML', 'CSS'],
      benefits: ['Health insurance', 'Retirement plan', 'Flexible work hours'],
      applicationDeadline: 'July 15, 2024',
      datePosted: 'June 10, 2024',
    },
    {
      title: 'Frontend Developer (React)',
      company: 'WebTech Innovators',
      location: 'New York, NY',
      jobType: 'Contract',
      salary: '$80 - $100 per hour',
      remoteOptions: 'Partially Remote',
      experienceLevel: 'Entry Level',
      educationLevel:
        'No formal education required, but relevant experience preferred',
      description:
        'Seeking a skilled React developer to collaborate on exciting new projects...',
      responsibilities: [
        'Work closely with designers and product managers to develop interactive user interfaces for web applications',
        'Develop and maintain high-quality reusable components using modern front-end frameworks',
        'Implement front-end designs using HTML, CSS, and JavaScript frameworks such as React',
        'Optimize application for maximum speed and scalability',
        'Collaborate with back-end developers and external partners to integrate front-end application with the RESTful API services',
      ],
      qualifications: [
        'Solid understanding of JavaScript, HTML5, and CSS3',
        'Experience with popular React.js workflows (such as Flux or Redux)',
        'Familiarity with RESTful APIs',
        'Knowledge of modern authorization mechanisms, such as JSON Web Token',
        'Proficient understanding of cross-browser compatibility issues and ways to work around them',
        'Familiarity with code versioning tools such as Git, SVN, etc.',
      ],
      skills: ['React', 'Redux', 'JavaScript', 'TypeScript', 'CSS'],
      benefits: ['Unlimited PTO', 'Remote work options', 'Team outings'],
      applicationDeadline: 'August 1, 2024',
      datePosted: 'June 12, 2024',
    },
    {
      title: 'Full Stack Developer (React/Node.js)',
      company: 'Tech Innovations Ltd.',
      location: 'Chicago, IL',
      jobType: 'Full-time',
      salary: '$90,000 - $110,000 per year',
      remoteOptions: 'Remote',
      experienceLevel: 'Mid Level',
      educationLevel: "Bachelor's Degree in Computer Science or related field",
      description:
        'We are looking for a talented Full Stack Developer to join our team...',
      responsibilities: [
        'Developing front end website architecture',
        'Designing user interactions on web pages',
        'Developing back-end website applications',
        'Creating servers and databases for functionality',
        'Ensuring cross-platform optimization for mobile phones',
        'Ensuring responsiveness of applications',
        'Working alongside graphic designers for web design features',
        'Seeing through a project from conception to finished product',
        'Designing and developing APIs',
        'Meeting both technical and consumer needs',
      ],
      qualifications: [
        'Strong organizational and project management skills',
        'Proficiency with fundamental front-end languages such as HTML, CSS, and JavaScript',
        'Familiarity with JavaScript frameworks such as Angular JS, React, and Amber',
        'Proficiency with server-side languages such as Python, Ruby, Java, PHP, and Net',
        'Familiarity with database technology such as MySQL, Oracle, and MongoDB',
        'Excellent verbal communication skills',
        'Good problem-solving skills',
        'Attention to detail',
      ],
      skills: ['React', 'Node.js', 'JavaScript', 'HTML', 'CSS'],
      benefits: ['Health insurance', '401(k) matching', 'Paid time off'],
      applicationDeadline: 'July 20, 2024',
      datePosted: 'June 8, 2024',
    },
    // Add more job objects as needed
  ];

  constructor() {}

  selectedJob: any = null;
  searchName: string = '';
  searchLocation: string = '';
  searchDate: string = '';
  filteredJobs: any[] = [];
  ngOnInit(): void {
    this.clearFilters();
    this.getJobs();
  }

  getJobs(): Observable<Job[]> {
    return of(this.jobs);
  }
  viewJobDetails(job: Job): void {
    this.selectedJob = job;
  }

  filterJobs(): void {
    this.filteredJobs = this.jobs.filter(
      (job) =>
        job.title.toLowerCase().includes(this.searchName.toLowerCase()) &&
        job.location
          .toLowerCase()
          .includes(this.searchLocation.toLowerCase()) &&
        job.datePosted.includes(this.searchDate)
    );
  }

  clearFilters(): void {
    this.searchName = '';
    this.searchLocation = '';
    this.searchDate = '';
    this.filteredJobs = this.jobs;
  }
  closePanel(): void {
    this.selectedJob = null;
  }
  applyForJob(job: any) {
    // Handle the job application process here
    console.log(`Applying for job: ${job.title}`);
    // You can redirect to an application page or trigger a modal here
  }
}
